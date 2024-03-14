package com.example.adminwaveoffood

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.adminwaveoffood.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var googleSignInClient: GoogleSignInClient
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                Log.e("task", task.toString())
                if (task.isSuccessful) {
                    val account: GoogleSignInAccount = task.result
                    Log.e("account", account.toString())
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    auth.signInWithCredential(credential).addOnCompleteListener { authTask ->
                        if (authTask.isSuccessful) {
                            Toast.makeText(
                                this, "Successfully sign - in with Google", Toast
                                    .LENGTH_SHORT
                            ).show()

                            updateUi(null)
                        } else {
                            Toast.makeText(
                                this, "Failed sign - in with Google", Toast
                                    .LENGTH_SHORT
                            ).show()
                        }
                    }


                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        database = Firebase.database.reference

        //login with google
        // initalize google sign in
        val goolgeSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)

            .requestIdToken("956709816198-mr7hkeei2ohdgrtldhnlub2ca54i36lr.apps.googleusercontent.com")
            .requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(this, goolgeSignInOption)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.btnGoogle.setOnClickListener {
            val signIntent = googleSignInClient.signInIntent
            launcher.launch(signIntent)
        }
        binding.btnLogin.setOnClickListener() {
            onClickLogin()
        }

        binding.tvDontHaveAccount.setOnClickListener() {
            startActivity(Intent(this, SignUpActivity::class.java))
        }


    }

    private fun onClickLogin() {

        email = binding.editTextEmail.text.toString().trim()
        password = binding.edtPassword.text.toString().trim()
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser
                    user = auth.currentUser!!
                    Toast.makeText(this, "Login Google button", Toast.LENGTH_SHORT).show()
                    Log.e("TAG","Login Google button"  )
                    updateUi(user)
                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        } else {

            Toast.makeText(this, "Please filled All", Toast.LENGTH_SHORT).show()

        }
    }

    private fun updateUi(user: FirebaseUser?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}