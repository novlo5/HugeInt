public class hugeTest{

    public static void main(String args[]){




    
        HugeInteger a = new HugeInteger("10000");
        HugeInteger b = new HugeInteger("120");

        //System.out.println(a.toString());
       //System.out.println(a.add(b));
       //System.out.println(a.subtract(b));
        System.out.println(a.multiply(b));
        System.out.println(a.fastMultiply(b));
        

    }  
}