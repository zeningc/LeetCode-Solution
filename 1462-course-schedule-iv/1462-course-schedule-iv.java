class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {


          boolean[][] floyed = new boolean[numCourses][numCourses];

          for(int [] pre : prerequisites){
               floyed[pre[0]][pre[1]] = true;
          }

          for(int mid = 0; mid < numCourses; mid++){

               for(int i = 0; i < numCourses; i++){

                    for(int j = 0; j < numCourses; j++){

                         floyed[i][j] = floyed[i][j]|| (floyed[i][mid] && floyed[mid][j]);
                    }
               }
          }

          List<Boolean> result = new ArrayList<>();

          for(int[] query : queries){

               int i = query[0];
               int j = query[1];

               result.add(floyed[i][j]);
          }
          return result;

    }
}
