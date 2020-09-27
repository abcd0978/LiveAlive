package lib;

import database.food;

public class binarySearch 
{
	private selctionSort ss;
	public binarySearch()
	{
		ss = new selctionSort();
	}
	public food binary_food(food[] arr,String key,int bywhat)
	{
		arr = ss.sort_food(arr,bywhat);//먼저 들어온 배열에대해 스트링소트
		int mid = 0;
        int left = 0;
        int right = arr.length - 1;
 
        while (right >= left) 
        {
            mid = (right + left) / 2;
            
            if (key == arr[mid].getName()) 
            {
                System.out.println(key + "is here" + mid);
                break;
            }
            if (key.compareTo(arr[mid].getName())<0) 
            {
                right = mid - 1;
            } 
            else 
            {
                left = mid + 1;
            }
        }
        return arr[mid];//찾는 스트링이 든 객체을 반환한다.
	}
}
