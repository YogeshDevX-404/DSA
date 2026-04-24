class Solution {
    public boolean checkIfExist(int[] arr) {
       Set<Integer> Set = new HashSet<>();
       for(int num: arr){
        if(Set.contains(2 * num) || (Set.contains(num / 2) && (num % 2 == 0))){
            return true;
        }
        Set.add(num);
       } 
       return false;
    }
}