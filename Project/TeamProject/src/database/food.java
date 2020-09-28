package database;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class food
{
	private SimpleStringProperty name;//이름
	private SimpleIntegerProperty serv;//1회제공량
	private SimpleDoubleProperty kcal;//열량
	private SimpleStringProperty unit;//단위
	private SimpleDoubleProperty protein;//단백질
	private SimpleDoubleProperty fat;//지방
	private SimpleDoubleProperty carb;//탄수화물
	public Object getNutinfos(int bywhat)//1.열량 2.탄수화물3.단백질4.지방 5.이름
	{
		if(bywhat==1)
			return this.kcal.doubleValue();
		else if(bywhat==2)
			return this.carb.doubleValue();
		else if(bywhat==3)
			return this.protein.doubleValue();
		else if(bywhat==4) 
			return this.fat.doubleValue();
		else
			return this.name.toString();
		
	}
	public void setNutinfo(double data,int bywhat)//1.열량2.탄수화물3.단백질4.지방
	{
		if(bywhat==1)
			this.kcal = new SimpleDoubleProperty(data);
		else if(bywhat==1)
			this.carb = new SimpleDoubleProperty(data);
		else if(bywhat==1)
			this.protein = new SimpleDoubleProperty(data);
		else if(bywhat==1)
			this.fat = new SimpleDoubleProperty(data);
	}
	public void setName(String name)//이름
	{
		this.name = new SimpleStringProperty(name);
	}
	public void setSerM(int serv)//1회제공량
	{
		this.serv= new SimpleIntegerProperty(serv);
	}
	public void setKcal(double kcal)//열량
	{
		this.kcal = new SimpleDoubleProperty(kcal);
	}
	public void setUnit(String unit)//단위
	{
		this.unit = new SimpleStringProperty(unit);
	}
	public void setProtein(double protein)//단백질
	{
		this.protein = new SimpleDoubleProperty(protein);
	}
	public void setFat(double fat)//지방
	{
		this.fat = new SimpleDoubleProperty(fat);
	}
	public void setCarb(double carb)//탄수화물
	{
		this.carb = new SimpleDoubleProperty(carb);
	}
	public double getKcal()
	{
		return this.kcal.get();
	}
	public double getProtein()
	{
		return this.protein.get();
	}
	public double getFat()
	{
		return this.fat.get();
	}
	public double getCarb()
	{
		return this.carb.get();
	}
	public String getName()//이름
	{
		return this.name.get();
	}
	public int getServ()//1회제공량
	{
		return this.serv.get();
	}
	public String getUnit()//단위
	{
		return this.unit.get();
	}
}
