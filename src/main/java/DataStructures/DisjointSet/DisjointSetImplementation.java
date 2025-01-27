package DataStructures.DisjointSet;

import java.util.Arrays;

public class DisjointSetImplementation {
    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(4);
        ds.unionBySize(1,2);
        ds.unionBySize(1,3);
        ds.unionBySize(1,4);

        System.out.println(ds.findUPar(1) + " " + ds.findUPar(2) + " " + ds.findUPar(3) + " " + ds.findUPar(4) );
    }
}
