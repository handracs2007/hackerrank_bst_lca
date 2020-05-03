import java.util.ArrayList;
import java.util.Scanner;

class Node {
    Node left;
    Node right;
    int data;

    Node ( int data ) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {

    public static Node lca ( Node root , int v1 , int v2 ) {
        ArrayList< Node > visitedNodes1 = new ArrayList<> ( );
        ArrayList< Node > visitedNodes2 = new ArrayList<> ( );

        Node curr = root;
        visitedNodes1.add ( curr );
        while ( curr.data != v1 ) {
            if ( v1 <= curr.data )
                curr = curr.left;
            else
                curr = curr.right;

            visitedNodes1.add ( curr );
        }

        curr = root;
        visitedNodes2.add ( curr );
        while ( curr.data != v2 ) {
            if ( v2 <= curr.data )
                curr = curr.left;
            else
                curr = curr.right;

            visitedNodes2.add ( curr );
        }

        for ( int i = visitedNodes1.size ( ) - 1 ; i >= 0 ; i-- ) {
            for ( int j = visitedNodes2.size ( ) - 1 ; j >= 0 ; j-- ) {
                if ( visitedNodes1.get ( i ) == visitedNodes2.get ( j ) ) {
                    return visitedNodes1.get ( i );
                }
            }
        }

        return null;
    }

    public static Node insert ( Node root , int data ) {
        if ( root == null ) {
            return new Node ( data );
        } else {
            Node cur;
            if ( data <= root.data ) {
                cur = insert ( root.left , data );
                root.left = cur;
            } else {
                cur = insert ( root.right , data );
                root.right = cur;
            }
            return root;
        }
    }

    public static void main ( String[] args ) {
        Scanner scan = new Scanner ( System.in );
        int t = scan.nextInt ( );
        Node root = null;
        while ( t-- > 0 ) {
            int data = scan.nextInt ( );
            root = insert ( root , data );
        }
        int v1 = scan.nextInt ( );
        int v2 = scan.nextInt ( );
        scan.close ( );
        Node ans = lca ( root , v1 , v2 );
        System.out.println ( ans.data );
    }
}