package com.example.fitnesscompetitionapp.ui.mainapp.domain.repository

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.fitnesscompetitionapp.ui.mainapp.data.repository.AuthRepository
import com.example.fitnesscompetitionapp.ui.mainapp.domain.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryIMPL @Inject constructor(
    private val auth: FirebaseAuth,
    private val fb: FirebaseFirestore
): AuthRepository {

    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    override suspend fun signUp(email: String, password: String): Resource<Boolean>
    =try{
        auth.createUserWithEmailAndPassword(email,password).
            addOnCompleteListener {
                if(it.isSuccessful)
                {
                    auth.currentUser?.sendEmailVerification()
                }
        }.await()
        Resource.Success(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun signIn(email: String, password: String): Resource<Boolean>
    =try{
        val boolResult = mutableStateOf(true)
        val result = auth.signInWithEmailAndPassword(email,password).await()
        Log.d("LoginFirebase","${result.user?.email}!")
        if(result.user == null)
            boolResult.value = false
        Log.d("LoginFirebase","${boolResult.value}")
        Resource.Success(boolResult.value)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun createUser(
        name: String,
        email: String,
        password: String
    ): Resource<Boolean>
    =try{
        val user = mutableMapOf<String,Any>()
        user["user_name"] = name
        user["user_email"] = email
        user["user_password"] = password
        fb.collection("users").document(email).set(user)
        Resource.Success(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override fun logOut() {
        auth.signOut()
    }

    override fun getAuthStateLogin(viewModelScope: CoroutineScope): StateFlow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),auth.currentUser == null)

    override fun getAuthStateData(viewModelScope: CoroutineScope): StateFlow<FirebaseUser?> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),auth.currentUser)
}