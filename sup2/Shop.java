package oop.sup2;

import java.util.LinkedList;

public class Shop {
	private LinkedList<ShopAssistant> assistants = new LinkedList<ShopAssistant>();
	private LinkedList<Department> departments = new LinkedList<Department>();
	private StoreManager manager;
}