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
		arr = ss.sort_food(arr,bywhat);//���� ���� �迭������ ��Ʈ����Ʈ
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
        return arr[mid];//ã�� ��Ʈ���� �� ��ü�� ��ȯ�Ѵ�.
	}
}
