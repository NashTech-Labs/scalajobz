package utils
import org.apache.commons.codec.binary.Hex

object ConversionUtility {

  /*
   * Does the encoding
   */
  val str = ""
  val scheme="cp424"
  val encodedString = Hex.encodeHexString(str.getBytes(scheme))

  /**
   * Decoding Part for a string
   * @param encodedString is the string to be decoded
   */
  def decodeMe(encodedString: String): String = {
    val originalString = new String(Hex.decodeHex(encodedString.toCharArray), scheme)
    originalString
  }

  /**
   * Encryption Of PassWord
   * @param password is the string to be encoded
   */

  def encryptPassword(password: String): String = {
    val encryptedpassword = Hex.encodeHexString(password.getBytes(scheme))
    encryptedpassword
  }

}
