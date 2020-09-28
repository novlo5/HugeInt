
public class HugeInteger implements Comparable<HugeInteger>{


    public static int digit_operations;

    private int[] hugeInt;


        public HugeInteger(String s){

            hugeInt = new int[s.length()];
            this.fill(s);

        }


        public void fill(String s){

            for(int i = 0; i < hugeInt.length; i++){

                int toFill = Character.getNumericValue(s.charAt(i));
                this.hugeInt[i] = toFill;

            }


        }

        
        public int compareTo(HugeInteger h){

            if(this.hugeInt.length != h.hugeInt.length){
                    return compareLen(this.hugeInt, h.hugeInt);
            }

            int i = 0;

            while(this.hugeInt[i] == h.hugeInt[i]){
                    if(i == this.hugeInt.length - 1){return 0;}
                        ++i;
            }
                    return compareVals(this.hugeInt[i], h.hugeInt[i]);

        }

        private int compareVals(int i, int j){

            if(i > j){return 1;}
                return -1;
        }


        private int compareLen(int[] arr1, int[] arr2){

                if(arr1.length > arr2.length){return 1;}

                    return -1;
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

            StringBuilder hugeInt = new StringBuilder(this.hugeInt.length);

            for(int i = 0; i < this.hugeInt.length; i++){

                hugeInt.append(this.hugeInt[i]);

            }

            return hugeInt.toString();
        }

}

            


