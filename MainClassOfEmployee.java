package com.company;

import java.util.Objects;
import java.util.Scanner;
class Employee{
     SingleNode head;
     SingleNode tail;
     SingleNode sorted;
     int size;

    public void createSingleLinkedList(int ID,String name,String email,String contactno,String designation,double salary) {
        head = new SingleNode();
        SingleNode node = new SingleNode();
        node.setID(ID);
        node.setName(name);
        node.setEmail(email);
        node.setContactNo(contactno);
        node.setDesignation(designation);
        node.setSalary(salary);
        node.setNext(null);
        head = node;
        tail = node;
        size = 1;
    }
    void insertionSort(SingleNode headOfNode) {
        sorted = null;
        SingleNode current = headOfNode;
        while (current != null) {
            SingleNode next = current.getNext();
            sortedInsert(current);
            current = next;
        }
        head = sorted;
    }
    void sortedInsert(SingleNode current) {
        if (sorted == null || sorted.getID() >= current.getID()) {
            current.setNext(sorted);
            sorted = current;
        }
        else {
            SingleNode node = sorted;
            while (node.getNext() != null && node.getNext().getID() < current.getID()) {
                node = node.getNext();
            }
            current.setNext(node.getNext());
            node.setNext(current);
        }
    }
    public void insertInLinkedList(int ID,String name,String email,String contactno,String designation,double salary, int location) {
        if (head == null){
            createSingleLinkedList(ID,name,email,contactno,designation,salary);
        }else {


            SingleNode node = new SingleNode();
            node.setID(ID);
            node.setName(name);
            node.setEmail(email);
            node.setContactNo(contactno);
            node.setDesignation(designation);
            node.setSalary(salary);
            if (existsLinkedList()) {
                System.out.println("The linked list does not exist!!");
                return;
            } else if (location == 0) {// insert at first position
                node.setNext(head);
                head = node;
            } else if (location >= size) {// insert at last position
                node.setNext(null);
                tail.setNext(node);
                tail = node;
            } else {// insert at specified location
                SingleNode tempNode = head;
                int index = 0;
                while (index < location - 1) {// loop till we reach specified node
                    tempNode = tempNode.getNext();
                    index++;
                }//tempNode currently references to node after which we should insert new node
                SingleNode nextNode = tempNode.getNext(); //this is the immediate next node after new node
                tempNode.setNext(node);//update reference of tempNode to reference to new node
                node.setNext(nextNode);//update newly added nodes' next.
            }
            setSize(getSize()+1);
        }
    }
    public SingleNode getHead() {
        return head;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public boolean existsLinkedList() {
        return head == null;
    }
    void traverseLinkedList(SingleNode tempNode) {
        System.out.println("----------------------------------------------------------------------");
        while (tempNode!=null) {
            System.out.println();
            System.out.print(tempNode.getID() + "  "  + tempNode.getName() + "  " + tempNode.getEmail() + "  " + tempNode.getDesignation() + "  "+ tempNode.getContactNo() + "  "+ tempNode.getSalary());
            tempNode = tempNode.getNext();
        }
        System.out.println("\n\n");
        System.out.println("----------------------------------------------------------------------");
    }
    //Deletes entire Linked List
    void deleteLinkedList() {
        System.out.println("\n\nDeleting Linked List...");
        head = null;
        tail = null;
        System.out.println("Linked List deleted successfully !");
    }
    //Searches a node with given value
    int searchForNode(int id) {
        SingleNode tempNode = head;
        for (int i = 0; i < getSize(); i++) {
            if (tempNode.getID() == id) {
                return i;
            }
            tempNode = tempNode.getNext();
        }
        return -1;
    }
    boolean searchForNodeANdTraverse(int id) {
        SingleNode tempNode = head;
        System.out.println("----------------------------------------------------------------------");
        for (int i = 0; i < getSize(); i++) {
            if (tempNode.getID() == id) {
                System.out.print(tempNode.getID() + "  " + tempNode.getName()+ "  " + tempNode.getEmail()+ "  " + tempNode.getDesignation()+ "  " + tempNode.getContactNo()+ "  " + tempNode.getSalary());
                return true;
            }
            tempNode = tempNode.getNext();
        }
        System.out.println("----------------------------------------------------------------------");
    return false;
    }
    //Deletes a node having a given value
    public void deletionOfNode(int location) {
        if (existsLinkedList()) {
            System.out.println("The linked list does not exist!!");
            return;
        } else if (location == 0) { // we want to delete first element
            head = head.getNext();
            setSize(getSize()-1);
            if(getSize() == 0) { 
                tail = null;
            }
        }else if (location >= getSize()){ //If location is not in range or equal, then delete last node
            SingleNode tempNode = head;
            for (int i = 0; i < size - 1; i++) {
                tempNode = tempNode.getNext(); 
            }
            if (tempNode == head) { 
                tail = head = null;
                setSize(getSize()-1);
                return;
            }
            tempNode.setNext(null);
            tail= tempNode;
            setSize(getSize()-1);
        }else { //if any inside node is to be deleted
            SingleNode tempNode = head;
            for (int i = 0; i < location - 1; i++) {
                tempNode = tempNode.getNext(); 
            }
            tempNode.setNext(tempNode.getNext().getNext()); 
            setSize(getSize()-1);
        }
    }

    public void editdata(int idd)
    {
        Scanner reader = new Scanner(System.in);
        SingleNode tempx=head;
        while(tempx.getNext()!=null) {
            tempx = tempx.getNext();
            if (tempx.getID() == idd) {
                break;
            }
        }
        if (tempx.getID() == idd) {
            System.out.println("---------Enter what you want to edit(In number)---------");
            System.out.println("1- ID");
            System.out.println("2- Name");
            System.out.println("3- Salary");
            System.out.println("4- Contact no");
            System.out.println("5- Email");
            System.out.println("6- Designation");
            int inpp = reader.nextInt();
            switch (inpp) {
                case 1:
                    System.out.println("Enter new ID of: " + tempx.getName());
                    int newID = reader.nextInt();
                    tempx.setID(newID);
                    System.out.println("-----------------Edit Successfully-----------------");
                    break;
                case 2:
                    System.out.println("Enter new Name of: " + tempx.getName());
                    String newname = reader.next();
                    tempx.setName(newname);
                    System.out.println("-----------------Edit Successfully-----------------");
                    break;
                case 3:
                    System.out.println("Enter new Salary of: " + tempx.getName());
                    double newsalary = reader.nextDouble();
                    tempx.setSalary(newsalary);
                    System.out.println("-----------------Edit Successfully-----------------");
                    break;
                case 4:
                    System.out.println("Enter new Contact no of: " + tempx.getName());
                    String newcontact_no = reader.next();
                    tempx.setContactNo(newcontact_no);
                    System.out.println("-----------------Edit Successfully-----------------");
                    break;
                case 5:
                    System.out.println("Enter new Email of: " + tempx.getName());
                    String newemail = reader.next();
                    tempx.setEmail(newemail);
                    System.out.println("-----------------Edit Successfully-----------------");
                    break;
                case 6:
                    System.out.println("Enter new Designation of : " + tempx.getName());
                    String newdes = reader.next();
                    tempx.setDesignation(newdes);
                    System.out.println("-----------------Edit Successfully-----------------");
                    break;
                default:
                    System.out.println("Enter between 1-7");
                    break;
            }
        }
        else
        {
            System.out.println(idd+" doesnot exist");
        }
    }
}
public class MainClassOfEmployee {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String Username = "Admin";
        String Password = "123";
        System.out.println("---------------------------Welcome to Employee Management System---------------------------");
        System.out.println("\nEnter Username: ");
        String username = reader.next();
        System.out.println("Enter Password: ");
        String password = reader.next();
        System.out.println();
        Employee list1 = new Employee();
        int user_choice = 0;
        if (Objects.equals(username, Username) && Objects.equals(password, Password)){
            System.out.println("-----------------Login success!-----------------\n");
            System.out.println("Press 0 to proceed");
            int c = reader.nextInt();
            do {
                System.out.println("\n");
                System.out.println("1-View all Employees.");
                System.out.println("2-Add an Employee.");
                System.out.println("3-Edit an Employee data");
                System.out.println("4-Delete data of an employee");
                System.out.println("5-Delete all Employee's data.");
                System.out.println("6-Search for an Employee.");
                System.out.println("7-Sort all the data wrt ID's");
                System.out.println("8-Exit");
                System.out.println("\n");
                user_choice = reader.nextInt();
                if (user_choice == 1){
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println("ID    Name    Email                   Designation Contact no  Salary");
                    list1.traverseLinkedList(list1.getHead());
                }else if (user_choice == 2) {
                    System.out.println("Enter ID: ");
                    int ID = reader.nextInt();
                    boolean check = false;
                    if (list1.searchForNode(ID)==-1)
                    {
                        check=true;
                    }
                    if (check) {
                        System.out.println("Enter Name: ");
                        String name = reader.next();
                        System.out.println("Enter Salary: ");
                        double salary = reader.nextDouble();
                        System.out.println("Enter Contact no: ");
                        String contact_no = reader.next();
                        System.out.println("Enter Email: ");
                        String email = reader.next();
                        System.out.println("Enter Designation: ");
                        String des = reader.next();
                        list1.insertInLinkedList(ID, name, email, contact_no, des, salary, 0);
                    }
                    else {
                        System.out.println("ID already exist");
                    }
                }
                else if (user_choice == 3) {
                    if (list1.getHead() == null) {
                        System.out.println("Data doesnot exist");
                    } else {
                        System.out.println("Enter ID of an employee which you want to edit: ");
                        int I__D = reader.nextInt();
                        list1.editdata(I__D);
                    }
                }
                else if (user_choice == 4){
                    if (list1.getHead() == null) {
                        System.out.println("Data doesnot exist");
                    } else {
                        System.out.println("Enter id: ");
                        int id = reader.nextInt();
                        if (list1.searchForNode(id) == -1) {
                            System.out.println(id + " doesnot exist");
                        } else {
                            list1.deletionOfNode(list1.searchForNode(id));
                            System.out.println("Employee deleted successfully with id " + id);
                        }
                    }
                }else if (user_choice == 5) {
                    if (list1.getHead() == null) {
                        System.out.println("Data doesnot exist");
                    } else {
                        list1.deleteLinkedList();
                        System.out.println("Successfully deleted all Employee's data.");
                    }
                }
                else if (user_choice == 6){
                    if (list1.getHead() == null) {
                        System.out.println("Data doesnot exist");
                    } else {
                        System.out.println("Enter Employee's ID: ");
                        int emp_id = reader.nextInt();
                        if (!list1.searchForNodeANdTraverse(emp_id)) {
                            System.out.println(emp_id + " doesnot exist");
                        }
                    }
                }else if (user_choice == 7) {
                    if (list1.getHead() == null) {
                        System.out.println("Data doesnot exist");
                    } else {
                        list1.insertionSort(list1.head);
                        System.out.println();
                        list1.traverseLinkedList(list1.getHead());
                        System.out.println();
                    }
                }
            }while (user_choice != 8);
        }else {
            System.out.println("Invalid username or password.");
        }
    }
}
