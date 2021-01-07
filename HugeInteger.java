import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HugeInteger implements Comparable<HugeInteger>{


    public static int DIGIT_OPERATIONS;

    private ArrayList<Integer> hugeInt;

    private boolean isNegative = false;


        public HugeInteger(String s){

            hugeInt = new ArrayList<Integer>();
            this.fill(s);

        }

        public HugeInteger(int len){

            hugeInt = new ArrayList<Integer>(len);;

        }


        public void fill(String s){

            for(int i = 0; i < s.length(); i++){

                int toFill = Character.getNumericValue(s.charAt(i));
                this.hugeInt.add(i, toFill);

            }
        }

        
        public int compareTo(HugeInteger h){

            if(this.hugeInt.size() != h.hugeInt.size()){
                    return compareLen(this.hugeInt, h.hugeInt);
            }

            int i = 0;

            while(this.hugeInt.get(i) == h.hugeInt.get(i)){
                    if(i == this.hugeInt.size()- 1){return 0;}
                        ++i;
                        HugeInteger.DIGIT_OPERATIONS += 2;
            }
                    return compareVals(this.hugeInt.get(i), h.hugeInt.get(i));

        }

        private int compareVals(int i, int j){

            if(i > j){return 1;}
                return -1;
        }


        private int compareLen(ArrayList<Integer> arr1, ArrayList<Integer> arr2){

            if(arr1.size() > arr2.size()){return 1;}
                return -1;
        }

       private int checkLen(ArrayList<Integer> arr1, ArrayList<Integer> arr2){

            if(arr1.size() > arr2.size()){return arr1.size();}
                return arr2.size();
        }



      public HugeInteger add(HugeInteger h){

            int carryIn = 0;
            int sum = 0;
            int newLen = checkLen(this.hugeInt, h.hugeInt);
            if(compareLen(this.hugeInt,h.hugeInt) == 1){
                h.fillzero(this.hugeInt.size() - h.hugeInt.size());
            }
            else if(compareLen(this.hugeInt,h.hugeInt) == -1){
                this.fillzero(h.hugeInt.size() - this.hugeInt.size());
            }
            HugeInteger add = new HugeInteger(newLen + 1);

            for(int i = newLen - 1; i >= 0; i--){

                sum = this.hugeInt.get(i) + h.hugeInt.get(i) + carryIn;

                if(sum < 10){

                        sum = sum % 10;
                        add.hugeInt.add(0, sum);
                        carryIn = 0;

                }

                else{

                        sum = sum % 10;
                        add.hugeInt.add(0, sum);
                        carryIn = 1;
                }
                HugeInteger.DIGIT_OPERATIONS += 4;
            }

            if(carryIn == 1){add.hugeInt.add(0, carryIn); HugeInteger.DIGIT_OPERATIONS++;}

            
            return add;

        } 

        public HugeInteger subtract(HugeInteger h){

            int carryIn = 0;
            int sum = 0;
            int newLen = checkLen(this.hugeInt, h.hugeInt);
            HugeInteger sub = new HugeInteger(newLen + 1);

            if(compareLen(this.hugeInt,h.hugeInt) == 1){
                h.fillzero(this.hugeInt.size() - h.hugeInt.size());
            }
            else if(compareLen(this.hugeInt,h.hugeInt) == -1){
                this.fillzero(h.hugeInt.size() - this.hugeInt.size());
            }

            if(this.compareTo(h) == -1){

                sub = h.subtract(this);
                sub.isNegative = true;
                return sub;

            }

            for(int i = newLen - 1; i >= 0; i--){

                sum = this.hugeInt.get(i) - h.hugeInt.get(i) - carryIn;


                if(sum >= 0){

                        sub.hugeInt.add(0, sum);
                        carryIn = 0;

                }

                else{

                        sum = sum + 10;
                        sub.hugeInt.add(0, sum);
                        carryIn = 1;
                }
                HugeInteger.DIGIT_OPERATIONS += 4;
            }

            
            return sub;

        }

        public HugeInteger multiply(HugeInteger h){

            int len1 = this.hugeInt.size(), len2 = h.hugeInt.size();
            Integer[] res = new Integer[len1+len2];
            HugeInteger result = new HugeInteger(len1+len2);
            Arrays.fill(res, 0);
            result.hugeInt.addAll(Arrays.asList(res));
               for(int i = len1 - 1; i >=0; i--){
                   for(int j = len2-1; j >=0; j--){

                    int prod = this.hugeInt.get(i)*h.hugeInt.get(j);

                    int temp = prod + result.hugeInt.get(i+j+1);

                    result.hugeInt.set(i+j+1, temp % 10);

                    int put = result.hugeInt.get(i + j) + temp / 10;

                    result.hugeInt.set(i+j, put);
                   }
                   HugeInteger.DIGIT_OPERATIONS+=6;
                }

            return result;
        }


        public HugeInteger fastMultiply(HugeInteger h){

            HugeInteger a;
            HugeInteger b;
            HugeInteger c;
            HugeInteger d;

            if(this.hugeInt.size() <= 5 || h.hugeInt.size() <= 5){
                    int m1 = Integer.parseInt(this.toString());
                    int m2 = Integer.parseInt(h.toString());
                    m1 = m1 * m2;
                    HugeInteger e = new HugeInteger(Integer.toString(m1));
                    return e;
            }

                int full = 0;

            if(this.hugeInt.size() % 2 != 0){
                this.fillzero(1);
            }

            if(h.hugeInt.size() % 2 != 0){
                h.fillzero(1);
            }
            
            if(this.compareTo(h) == 1){
                full = this.hugeInt.size();
                     h.fillzero(this.hugeInt.size() - h.hugeInt.size());
            }
                else{
                    full= h.hugeInt.size();
                   this.fillzero(h.hugeInt.size() - this.hugeInt.size());
                }
            
               int half = full / 2; // splitting up for a b c d

                a = new HugeInteger(full);
                    a.createList(this.hugeInt, 0 , half);
                b = new HugeInteger(full);
                    b.createList(this.hugeInt, half , this.hugeInt.size());
                c = new HugeInteger(full);
                    c.createList(h.hugeInt, 0, half);
                d = new HugeInteger(full);
                    d.createList(h.hugeInt, half, h.hugeInt.size());

                    HugeInteger ac = a.fastMultiply(c);
                    HugeInteger bd = b.fastMultiply(d);
                    HugeInteger ab = a.add(b);
                    HugeInteger abcd = ab.fastMultiply(c.add(d));
                    abcd = (abcd.subtract(ac)).subtract(bd);

                    ac.power(2*half);
                    abcd.power(half);


            return  (ac.add(abcd)).add(bd);

        }

        private void createList(ArrayList<Integer> a, int from, int to){
                for(int i = from; i < to; i++){
                    this.hugeInt.add(a.get(i));
                }
        }

        private void fillzero(int toFill){
                for(int i = 0; i < toFill; i++){
                        this.hugeInt.add(0, 0);
                }

        }

        private void power(int toFill){
            for(int i = 0; i < toFill; i++){
                    this.hugeInt.add(0);
            }

    }




        public String toString(){

            boolean leadzero = false;
            char neg = '-';

            StringBuilder hugeInt = new StringBuilder(this.hugeInt.size());

            for(int i = 0; i < this.hugeInt.size(); i++){

                        if(this.hugeInt.get(i) == 0 && !leadzero){continue;}

                hugeInt.append(this.hugeInt.get(i));
                leadzero = true;

            }
            if(this.isNegative){hugeInt.insert(0, neg);}
            if(!leadzero){hugeInt.append(0);}

            return hugeInt.toString();
        }

}

            


