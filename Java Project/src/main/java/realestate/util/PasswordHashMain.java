package realestate.util;
import java.util.Arrays;
/** One-time local helper for provisioning an administrator; do not use on a shared terminal. */
public final class PasswordHashMain {
 public static void main(String[] args){char[] password=System.console()!=null?System.console().readPassword("New admin password: "):new java.util.Scanner(System.in).nextLine().toCharArray();try{if(password.length<8)throw new IllegalArgumentException("Use at least 8 characters.");System.out.println(PasswordUtil.hash(password));}finally{Arrays.fill(password,'\0');}}
}
