package utils

import org.apache.commons.lang.RandomStringUtils
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object PasswordHashing {

  /**
   * Generate Random Alphanumeric String of Length 10 For Password
   */
  def generateRandomPassword: String = {
    RandomStringUtils.randomAlphanumeric(10)
  }

  /**
   * Password Hashing Using Message Digest Algo
   */
  def encryptPassword(password: String): String = {
    val algorithm: MessageDigest = MessageDigest.getInstance("SHA-256")
    val defaultBytes: Array[Byte] = password.getBytes
    algorithm.reset
    algorithm.update(defaultBytes)
    val messageDigest: Array[Byte] = algorithm.digest
    getHexString(messageDigest)
  }

  /**
   * Generate HexString For Password Encryption
   */
  def getHexString(messageDigest: Array[Byte]): String = {
    val hexString: StringBuffer = new StringBuffer
    messageDigest foreach { digest =>
      val hex = Integer.toHexString(0xFF & digest)
      if (hex.length == 1) hexString.append('0')
      else hexString.append(hex)
    }
    hexString.toString
  }

}