
public class HugeInteger implements Comparable<HugeInteger>{


    public static int digit_operations;

    private int[] huge_int;


        public HugeInteger(String s){

            huge_int = new int[s.length()];
            this.fill(s);

        }


        public void fill(String s){

            for(int i = 0; i < huge_int.length; i++){

                int toFill = Character.getNumericValue(s.charAt(i));
                this.huge_int[i] = toFill;

            }


        }

        
        public int compareTo(HugeInteger h){

            return 0;

        }

        public int add(HugeInteger h){

            return 0;

        }

        public int subtract(HugeInteger h){

            return 0;

        }

        public int multiply(HugeInteger h){

            return 0;

        }


        public int fastmultiply(HugeInteger h){

            return 0;

        }


        public String toString(){

            StringBuilder hugeInt = new StringBuilder(this.huge_int.length);

            for(int i = 0; i < huge_int.length; i++){

                hugeInt.append(this.huge_int[i]);

            }

            return hugeInt.toString();
        }

}

            


