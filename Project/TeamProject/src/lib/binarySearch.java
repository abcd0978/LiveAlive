package lib;

public class binarySearch 
{
	private selctionSort ss;
	public binarySearch()
	{
		ss = new selctionSort();
	}
	public String binary_String(String[] arr,String key)
	{
		arr = ss.sort_String(arr);
		int mid = 0;
        int left = 0;
        int right = arr.length - 1;
 
        while (right >= left) 
        {
            mid = (right + left) / 2;
 
            if (key == arr[mid]) {
                System.out.println(key + "is here" + mid);
                break;
            }
            if (key.compareTo(arr[mid])<0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return arr[mid];
	}
	public int binary_int(int[] arr,int key)
	{
		arr = ss.sort_int(arr);
		int mid = 0;
        int left = 0;
        int right = arr.length - 1;
 
        while (right >= left) 
        {
            mid = (right + left) / 2;
 
            if (key == arr[mid]) {
                System.out.println(key + "is here" + mid);
                break;
            }
            if (key < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return arr[mid];
	}
}
