package com.dkatalis.parkinglot.assignment.model;

public class Slot {
	 private int id;
	 private int level;

	    public Slot(int id,int level) {
	        this.id = id;
	        this.level=level;
	    }

	    public int getId() {
	        return id;
	    }

	    public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public void setId(int id) {
	        this.id = id;
	    }

	    @Override
	    public String toString() {
	        return "Slot{" +
	                "id=" + id +
	                '}';
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Slot slot = (Slot) o;

	        return ((id == slot.id)&&(level==slot.level));

	    }

	    @Override
	    public int hashCode() {
	    	int prime=7;
	        return prime+id+level;
	    }
}
