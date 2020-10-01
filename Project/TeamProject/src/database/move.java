package database;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class move 
{
	private SimpleIntegerProperty kcal;
	private SimpleStringProperty name;
	public void setkcal(int kcal)
	{
		this.kcal = new SimpleIntegerProperty(kcal);
	}
	public void setName(String name)
	{
		this.name = new SimpleStringProperty(name);
	}
	public String getName()
	{
		return this.name.get();
	}
	public int getKcal()
	{
		return this.kcal.get();
	}
	public SimpleStringProperty getNameP()
	{
		return this.name;
	}
	public SimpleIntegerProperty getKcalP()
	{
		return this.kcal;
	}
}
