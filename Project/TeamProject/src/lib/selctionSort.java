package lib;
import database.food;
import database.move;
public class selctionSort
{
    public String[] sort_String(String[] data)//스트링 정렬
    {
        int size = data.length;
        int min;
        String temp;
        
        for(int i=0; i<size-1; i++)
        {
            min = i;
            for(int j=i+1; j<size; j++){
                if(data[min].compareTo(data[j])>0){
                    min = j;
                }
            }
            temp = data[min];
            data[min] = data[i];
            data[i] = temp;
        }
        return data;
    }    
    public food[] sort_food(food[] data,int bywhat)//1은 이름순, 2는 
    {
        int size = data.length;
        int min;
        food temp;
        
        for(int i=0; i<size-1; i++)
        {
            min = i;
            for(int j=i+1; j<size; j++)
            {
            	if(bywhat==5)
            	{
            		 if(((String)data[min].getNutinfos(bywhat)).compareTo(((String)(data[j].getNutinfos(bywhat)))) < 0 )
                     {
                         min = j;
                     }
            	}
            	else
            	{
            		 if((double)data[min].getNutinfos(bywhat) > (double)(data[j].getNutinfos(bywhat)))
                     {
                         min = j;
                     }
            	}
            }
            temp = data[min];
            data[min] = data[i];
            data[i] = temp;
        }
        return data;
    }
    public move[] sort_moves(move[] data,int top)//스트링 정렬
    {
        int size = top+1;
        int min;
        move temp;
        
        for(int i=0; i<size-1; i++)
        {
            min = i;
            for(int j=i+1; j<size; j++){
                if(data[min].getKcal()>data[j].getKcal()){
                    min = j;
                }
            }
            temp = data[min];
            data[min] = data[i];
            data[i] = temp;
        }
        return data;
    }   
}
