package utils
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import sun.misc.BASE64Decoder
import sun.misc.BASE64Encoder
import play.api.Play

class PasswordHashing {

  val ALGO = Play.current.configuration.getString("algo").get
  val keyValue: Array[Byte] = Play.current.configuration.getString("encryptionKey").get.toCharArray.map(_.toByte)

  /**
   * Creates The Unique Key For The Purpose Of Encryption & Decryption 
   */
  def generateKey : SecretKeySpec = {
    val key = new SecretKeySpec(keyValue, ALGO)
    key
  }

  /**
   * Encryption Of Password By AES
   */
  def encryptThePassword(password: String):String = {
    val key = generateKey
    val cipher = Cipher.getInstance(ALGO)
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val encVal = cipher.doFinal(password.getBytes())
    val encryptedPassword = new BASE64Encoder().encode(encVal)
    encryptedPassword
  }

  /**
   * Decryption Of Password By AES
   */
  def decryptThePassword(encryptedPassword: String) : String  = {
    val key = generateKey
    val cipher = Cipher.getInstance(ALGO)
    cipher.init(Cipher.DECRYPT_MODE, key)
    val decordedValue = new BASE64Decoder().decodeBuffer(encryptedPassword)
    val decValue = cipher.doFinal(decordedValue)
    val decryptedpassword = new String(decValue)
    decryptedpassword
  }

}
