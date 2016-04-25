package testrouter;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Name: Zorigtbaatar Chuluundorj
 * File: MacAddressTree.java
 * The file contains the class MacAddressTree.java
 * This class is responsible for holding Mac address of connectable
 * devices. Furthermore, this class is responsible for finding the
 * shortest route to the specified Mac address.
 */
public class MacAddressTree {

    private List<MacAddressTree> branch;
    private List<String> shortestpath;
    private List<String> visiteddevices;
    private String thisdevice;

    //Class constructor
    //The constructor initiliazes its own mac address
    //and initiliazes the remaining variables.
    public MacAddressTree(String thisdevice) {
        this.setThisDevice(thisdevice);
        this.shortestpath = new ArrayList<>();
        this.branch = new ArrayList<>();
        this.visiteddevices = new ArrayList<>();
    }

    //adds a branch to the node
    public void addBranch(MacAddressTree branch) {
        this.branch.add(branch);
    }

    //removes specified branch
    public void removeBranch(MacAddressTree branch) {
        this.branch.remove(branch);
    }

    //returns the nth branch
    public MacAddressTree getBranch(int branchnumber) {
        if (branchnumber < branch.size()) {
            return branch.get(branchnumber);
        }
        return null;
    }

    //gets device mac address
    public String getDevice() {
        return thisdevice;
    }

    //sets device mac address
    private void setThisDevice(String device) {
        this.thisdevice = device;
    }
    
    //returns the deepestnode of this mac address tree
    private int getDeepestNode () {
        int deepestnode = 1;
        int branchdeepestnode = 1;
        
        if(branch.size() == 0) {
            return 0;
        }
        
        for(int counter = 0; counter < branch.size(); counter ++) {
            if(branchdeepestnode < this.getBranch(counter).getDeepestNode()) {
                branchdeepestnode = this.getBranch(counter).getDeepestNode();
            }
        }
        return deepestnode + branchdeepestnode;
    }
    

    //The below function finds the shortest path to a device
    //Breadth first algorithm is used to find the shortestpath
    protected List<String> getShortestPath(String device,
            List<String> visiteddevices, List<String> shortestpath,
            int depthcounter) {
        
        //If the depthcounter reaches zero it is time to return the result
        if (depthcounter == 0) {
            return shortestpath;
        }

        depthcounter--;
        
        //Add device mac address to visited device address
        visiteddevices.add(getDevice());
        if (device.equals(this.getDevice())) {
            shortestpath.add(this.getDevice());
            return shortestpath;
        }

        //The below loop is used for searching all
        //Furthermore it checks if the branch has been visited before.
        //If so skip the visited branch
        for (int counter = 0; counter < branch.size(); counter++) {

            if (!((visiteddevices.contains(branch.get(counter).getDevice()))
                    || (branch.get(counter).getShortestPath(device,
                            visiteddevices, shortestpath, depthcounter).isEmpty()))) {
                shortestpath.add(this.getDevice());
                return shortestpath;
            }

        }

        //If we get here the requested, device is not in the routing table
        //shortestpath should be an empty list
        return shortestpath;
    }
    
    
    
    

    //This class is called to find the route to the requested devices
    //The devie is found by using breadthfirst algorithm
    //Furthermore, the function gets the deepest node in the tree and uses
    //that node as the limit
    //It clears the previous shortestapth and visiteddevices.
    public List<String> getPath(String device) {
        int depthcounter;
        shortestpath.clear();
        visiteddevices.clear();

        for (depthcounter = 1; depthcounter > 0 &&
                depthcounter <= this.getDeepestNode(); depthcounter++) {
            getShortestPath(device, visiteddevices, shortestpath, depthcounter);
            if ( !shortestpath.isEmpty() ) {
                depthcounter = -100;
            }
            visiteddevices.clear();
        }

        Collections.reverse(shortestpath);
        return shortestpath;
    }
}
