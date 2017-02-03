//package write.your.package;

public class DescriptorReader {
	
	public static String Descriptor2String(Mat descriptor){
		return arrayToString(DescriptorReader.descriptorToArray(descriptor));
	}
	
	public static Mat String2Descriptor(String str){
		return arrayToDescriptor(DescriptorReader.stringToArray(str));
	}

	private static int[][] descriptorToArray(Mat descriptor){
     int cols = descriptor.cols();
     int rows = descriptor.rows();
     int[][] result = new int[rows][cols];
     for(int i = 0; i < rows; i++){
         for(int j = 0; j < cols; j++){
             double[] d = descriptor.get(i, j);
             result[i][j] = (int)d[0];
         }
     }
     return result;
 }

 private static Mat arrayToDescriptor(int[][] array){
     int a = array.length;
     int b = array[0].length;
     Mat descriptor = new Mat(array.length, array[0].length, CvType.CV_8UC1);
     for(int i = 0; i < a; i++){
         for(int j = 0; j < b; j++){
         		descriptor.put(i, j, array[i][j]);
         }
     }
     return descriptor;
 }

 private static String arrayToString(int[][] array){
     StringBuffer sb = new StringBuffer();
     for(int[] eachArray: array){
         for(int each: eachArray){
             sb.append(each + ",");
         }
     }
     String str = sb.toString();
     return str;
 }

 private static int[][] stringToArray(String string) {
     String[] strings = string.split(",");
     int cols = strings.length / 32;
     int[][] result = new int[cols][32];

     for(int i = 0; i < cols; i++){
         for(int j = 0; j < 31; j++){
             result[i][j] = Integer.parseInt(strings[i * 32 + j]);
         }
     }
     return result;
 }
}
