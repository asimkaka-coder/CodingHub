package com.example.codinghub.auth.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codinghub.Event
import com.example.codinghub.auth.data.models.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject


const val USERS = "users"

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val db: FirebaseFirestore
) : ViewModel() {


    val userDataVM = mutableStateOf<User?>(null)
    val inProgress = mutableStateOf(false)
    val signedIn = mutableStateOf(false)
    val popUpNotification = mutableStateOf<Event<String>?>(null)


    init {
        val currentUser = firebaseAuth.currentUser
        signedIn.value = currentUser!=null
        currentUser?.uid?.let {
            getUserData(it)
        }
    }


    fun signOut() = viewModelScope.launch {
        inProgress.value = true
        firebaseAuth.signOut()
        inProgress.value = false
        signedIn.value = false
        userDataVM.value = null
    }

    suspend fun signInUser(email: String,password: String){

        viewModelScope.launch {
            inProgress.value = true
            try {
                val auth = firebaseAuth.signInWithEmailAndPassword(email,password).await()
                if(auth.user!=null){
                    signedIn.value = true
                    inProgress.value = false
                    firebaseAuth.currentUser?.uid?.let {
                        getUserData(it)
                    }
                }else{

                    handleException(customError = "Login Failed")
                    inProgress.value = false
                }
            }catch (e:Exception){
                handleException(e,"Fucked!")
                inProgress.value = false
            }

        }
    }

    suspend fun signUpUser(
        username: String,
        email: String,
        password: String,
        fullName: String
    ) {

        viewModelScope.launch {
            inProgress.value = true
            val documents = db.collection(USERS).whereEqualTo("userName", username).get().await()
            if (documents.size() > 0) {
                handleException(customError = "Username already taken")
                inProgress.value = false
                return@launch
            } else {
                try {
                    val authResult =
                        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                    if (authResult.user != null) {
                        signedIn.value = true
                        createUserProfile(username = username, name = fullName)
                        inProgress.value = false
                    } else {
                        inProgress.value = false
                        handleException(customError = "Something went Wrong!")
                    }
                } catch (e: Exception) {
                    inProgress.value = false
                    handleException(exception = e, customError = "Something went Wrong!")
                }

            }


        }
    }

    private suspend fun createUserProfile(
        name: String? = null,
        bio: String? = null,
        imageUrl: String? = null,
        username: String? = null
    ) {

        val uid = firebaseAuth.currentUser?.uid
        val userData = User(
            userID = uid,
            userName = username ?: userDataVM.value?.userName,
            fullName = name ?: userDataVM.value?.fullName,
            imageURL = imageUrl ?: userDataVM.value?.imageURL,
            bio = bio ?: userDataVM.value?.bio,
            following = userDataVM.value?.following
        )

        uid?.let { uid ->
            inProgress.value = true
            viewModelScope.launch {
                try {
                    val registerDocumentSnapshot = db.collection(USERS).document(uid).get().await()
                    if (registerDocumentSnapshot.exists()) {
                        registerDocumentSnapshot.reference.update(userData.toMap()).await()
                        userDataVM.value = userData
                        inProgress.value = false
                    } else {
                        db.collection(USERS).document(uid).set(userData)
                        getUserData(uid)
                    }

                } catch (e: Exception) {
                    handleException(e, "Something Fucked.. cant create user")
                    inProgress.value = false
                }
            }
        }
    }

    private fun getUserData(uid: String) {

        inProgress.value = true
        db.collection(USERS).document(uid).get().addOnSuccessListener {
            val user = it.toObject<User>()
            userDataVM.value = user
            inProgress.value = false
            popUpNotification.value = Event("User Data Retrieved Successfully")
        }
            .addOnFailureListener {
                handleException(it,"Cannot retrieve User Data")
                inProgress.value = false
            }

    }


    fun handleException(exception: Exception? = null, customError: String = "") {
        exception?.printStackTrace()
        val errorMessage = exception?.localizedMessage ?: customError
        popUpNotification.value = Event(errorMessage)
    }
}