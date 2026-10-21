package realestate.util;
import javax.crypto.SecretKeyFactory; import javax.crypto.spec.PBEKeySpec; import java.security.*; import java.util.Base64;
public final class PasswordUtil {
 private PasswordUtil(){}
 public static String hash(char[] password){try{byte[] salt=new byte[16];SecureRandom.getInstanceStrong().nextBytes(salt);byte[] h=derive(password,salt);return "pbkdf2$210000$"+Base64.getEncoder().encodeToString(salt)+"$"+Base64.getEncoder().encodeToString(h);}catch(GeneralSecurityException e){throw new IllegalStateException("Password hashing unavailable",e);}}
 public static boolean verify(char[] password,String encoded){try{String[] p=encoded.split("\\$");if(p.length!=4||!p[0].equals("pbkdf2"))return false;byte[] salt=Base64.getDecoder().decode(p[2]),want=Base64.getDecoder().decode(p[3]);return MessageDigest.isEqual(want,derive(password,salt));}catch(Exception e){return false;}}
 private static byte[] derive(char[] p,byte[] salt)throws GeneralSecurityException{PBEKeySpec spec=new PBEKeySpec(p,salt,210000,256);try{return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();}finally{spec.clearPassword();}}
}
