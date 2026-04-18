public class Password{
static int size = 10;  
public static String Reshape(int size){
        String password="";
    
        String LowerCase="abcdefghijklmnopqrstuvwxyz";
        String UpperCase="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Digits="0123456789";
        String SpecialCharacters="!@#$%^&*()-_=+[]{}|;:\"<>,.?/";

        int r=(int)(3*Math.random());  
        int i=0;

        switch (r) {
            case 0:
                while(i<size){
                    int r1=(int)(LowerCase.length()*Math.random());
                    password+=LowerCase.charAt(r1);
                    int r2=(int)(UpperCase.length()*Math.random());
                    password+=UpperCase.charAt(r2);
                    int r3=(int)(Digits.length()*Math.random());
                    password+=Digits.charAt(r3);
                    i++;
                }
                break;
            case 1:
                while(i<size){
                    int r1=(int)(UpperCase.length()*Math.random());
                    password+=UpperCase.charAt(r1);
                    int r2=(int)(Digits.length()*Math.random());
                    password+=Digits.charAt(r2);
                    int r3=(int)(SpecialCharacters.length()*Math.random());
                    password+=SpecialCharacters.charAt(r3);
                    i++;
                }
                break;
                case 2:
                while(i<size){
                    int r1=(int)(LowerCase.length()*Math.random());
                    password+=LowerCase.charAt(r1);
                    int r2=(int)(Digits.length()*Math.random());
                    password+=Digits.charAt(r2);
                    int r3=(int)(SpecialCharacters.length()*Math.random());
                    password+=SpecialCharacters.charAt(r3);
                    i++;
                }
                 break;
            default:
                break;
        }
        return password;
                
    }
    
}