package database;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class food
{
	private SimpleStringProperty name;//�̸�
	private SimpleIntegerProperty serv;//1ȸ������
	private SimpleDoubleProperty kcal;//����
	private SimpleStringProperty unit;//����
	private SimpleDoubleProperty protein;//�ܹ���
	private SimpleDoubleProperty fat;//����
	private SimpleDoubleProperty carb;//ź��ȭ��
	public Object getNutinfos(int bywhat)//1.���� 2.ź��ȭ��3.�ܹ���4.���� 5.�̸�
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
	public void setNutinfo(double data,int bywhat)//1.����2.ź��ȭ��3.�ܹ���4.����
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
	public void setName(String name)//�̸�
	{
		this.name = new SimpleStringProperty(name);
	}
	public void setSerM(int serv)//1ȸ������
	{
		this.serv= new SimpleIntegerProperty(serv);
	}
	public void setKcal(double kcal)//����
	{
		this.kcal = new SimpleDoubleProperty(kcal);
	}
	public void setUnit(String unit)//����
	{
		this.unit = new SimpleStringProperty(unit);
	}
	public void setProtein(double protein)//�ܹ���
	{
		this.protein = new SimpleDoubleProperty(protein);
	}
	public void setFat(double fat)//����
	{
		this.fat = new SimpleDoubleProperty(fat);
	}
	public void setCarb(double carb)//ź��ȭ��
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
	public String getName()//�̸�
	{
		return this.name.get();
	}
	public int getServ()//1ȸ������
	{
		return this.serv.get();
	}
	public String getUnit()//����
	{
		return this.unit.get();
	}
}
