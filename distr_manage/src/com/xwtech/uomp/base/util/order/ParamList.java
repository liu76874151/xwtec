package com.xwtech.uomp.base.util.order;

import java.util.ArrayList;



public class ParamList {

    transient private static org.apache.commons.logging.Log log =
            org.apache.commons.logging.LogFactory.getLog( ParamList.class );

    private ArrayList list = new ArrayList();

 
    public ArrayList get()
    {
        return list;
    }

  
    public void set( ParamList pList )
    {
        if ( pList != null )
            list = pList.get();
        else
            list = new ArrayList();
    }

  
    public void set( ArrayList aList )
    {
        if ( aList != null )
            list = aList;
        else
            list = new ArrayList();
    }

 
    public void add( final Parameter param )
    {
        if ( list == null )
            list = new ArrayList();

        list.add( param );
    }

    public Parameter remove( final int index )
    {
        Parameter tmpPara = null;
        try
        {
            tmpPara = ( Parameter ) list.remove( index );
        }
        catch ( Exception e )
        {
            if ( log.isErrorEnabled() )
                log.error( "ParamList.remove[" + index + "], no object at [" + index + "]" );
        }

        return tmpPara;
    }

    public Parameter remove( final String name )
    {
        for ( int i = 0; i < size(); i++ )
        {
            if ( get( i ) != null && get( i ).getName() != null && get( i ).getName().equals( name ) )
            {
                return ( Parameter ) list.remove( i );
            }
        }
        return null;
    }

   
    public Parameter get( final int index )
    {
        Parameter param = null;
        if ( list != null )
        {
            try
            {
                param = ( Parameter ) list.get( index );
            }
            catch ( Exception e )
            {
                param = null;
            }
        }

        return param;
    }

   
    public Parameter get( final String name )
    {
        Parameter param = null;

        for ( int i = 0; i < size(); i++ )
        {
            final Parameter tmpPara = get( i );
            if ( tmpPara != null )
            {
                if ( tmpPara.getName() != null && tmpPara.getName().equalsIgnoreCase( name ) )
                {
                    param = tmpPara;
                    break;
                }
            }
        }
        return param;
    }

    public int size()
    {
        return ( list != null ? list.size() : 0 );
    }

    public String listParameters()
    {
        String retString = "";

        if ( list != null && list.size() > 0 )
        {
            for ( int i = 0; i < list.size(); i++ )
            {
                retString += list.get( i ) + "\n";
            }

            return retString;
        }

        return retString;
    }


    public String toString()
    {
        String retString = "";

        if ( list != null && list.size() > 0 )
        {
            for ( int i = 0; i < list.size(); i++ )
            {
                retString += list.get( i ) + "\n";
            }

            return retString;
        }
        else
            return super.toString();
    }


}
