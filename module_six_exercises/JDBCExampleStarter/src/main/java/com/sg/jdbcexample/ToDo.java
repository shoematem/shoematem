/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.jdbcexample;

/**
 *
 * @author kylerudy
 */

public class ToDo
{
    private int ID;
    private String toDo;
    private String note;
    private boolean finished;

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getToDo()
    {
        return toDo;
    }

    public void setToDo(String toDo)
    {
        this.toDo = toDo;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }
}