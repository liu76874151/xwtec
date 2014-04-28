package com.xwtech.uomp.base.util.order;

import java.util.ArrayList;


public class Parameter {

	transient private static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(Parameter.class);

	private String paraName = "";
	private ArrayList paraValues = null;

	
	public Parameter()
	{
		paraName = "";
	}

	
	public Parameter(final String name)
	{
		paraName = name;
	}

	
	public Parameter(final String name, final String value)
	{
		paraName = name;
		paraValues = new ArrayList();
		paraValues.add(value);
	}

	
	public Parameter(final String name, final Object object)
	{
		paraName = name;
		paraValues = new ArrayList();
		paraValues.add(object);
	}

	
	public Parameter(final String name, final ArrayList values)
	{
		paraName = name;
		paraValues = values;
	}

	
	public String getName()
	{
		return paraName;
	}

	
	public void setName(final String name)
	{
		paraName = name;
	}

	
	public String getValue()
	{
		String retString = null;

		if ( paraValues != null && paraValues.size() > 0 )
			retString = (String) paraValues.get(0);

		return retString;
	}

	
	public Object getObject()
	{
		Object retObject = null;

		if ( paraValues != null && paraValues.size() > 0 )
			retObject = paraValues.get(0);

		return retObject;
	}

	
	public String getValue(final int index)
	{
		String retString = null;

		if ( paraValues != null && paraValues.size() > index )
			retString = (String) paraValues.get(index);

		return retString;
	}

	
	public Object getObject(final int index)
	{
		Object retObject = null;

		if ( paraValues != null && paraValues.size() > index )
			retObject = paraValues.get(index);

		return retObject;
	}

	
	public ArrayList getValues()
	{
		return paraValues;
	}

	
	public ArrayList getObjects()
	{
		return paraValues;
	}

	public void setValues(ArrayList values)
	{
		if ( paraValues == null )
			paraValues = new ArrayList();
		else
			paraValues.clear();

		paraValues.addAll(values);
	}

	
	public void setObjects(ArrayList values)
	{
		if ( paraValues == null )
			paraValues = new ArrayList();
		else
			paraValues.clear();

		paraValues.addAll(values);
	}

	
	public void addValue(final String value)
	{
		if ( paraValues == null )
			paraValues = new ArrayList();

		paraValues.add(value);
	}

	
	public void addObject(final Object object)
	{
		if ( paraValues == null )
			paraValues = new ArrayList();

		paraValues.add(object);
	}

	
	public void addValues(final ArrayList values)
	{
		if ( paraValues == null )
			paraValues = new ArrayList();

		try
		{
			paraValues.addAll(values);
		}
		catch (Exception e)
		{
			log.error(e);
		}
	}

	
	public void addObjects(final ArrayList objects)
	{
		if ( paraValues == null )
			paraValues = new ArrayList();

		try
		{
			paraValues.addAll(objects);
		}
		catch (Exception e)
		{
			log.error(e);
		}
	}

	public void clear()
	{
		if ( paraValues != null )
			paraValues.clear();
	}

	public String listValues()
	{
		if ( paraName == null )
			return "";

		String retString = paraName + "=[";

		if ( paraValues != null && paraValues.size() > 0 )
		{
			for (int j = 0; j < paraValues.size() - 1; j++)
			{
				retString += paraValues.get(j) + ",";
			}
			retString += paraValues.get(paraValues.size() - 1);
		}

		retString += "]";

		return retString;
	}

	public String toString()
	{
		if ( paraName != null )
		{
			String retString = paraName + "=[";

			if ( paraValues != null && paraValues.size() > 0 )
			{
				for (int j = 0; j < paraValues.size() - 1; j++)
				{
					retString += paraValues.get(j) + ",";
				}
				retString += paraValues.get(paraValues.size() - 1);
			}

			retString += "]";

			return retString;
		}
		else
			return super.toString();
	}



}
