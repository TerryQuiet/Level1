package tk.quietdev.level1.data

import tk.quietdev.level1.R

data class User(
    var userName: String? = null,
    var email: String,
    var occupation: String = "Not provided",
    var physicalAddress: String = "Not provided",
    var picture: String = "https://i.pravatar.cc/150?u=${email}",
    private var password: String = "11111"
) {
    fun isPasswordCorrect(inputPassword: String): Boolean {
        return password == inputPassword
    }

    companion object {
        const val USERNAME = "userName"
        const val OCCUPATION = "occupation"
        const val PHYSICAL_ADDRESS = "physicalAddress"
        const val PICTURE = "picture"
    }
}


