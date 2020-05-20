/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
How would you optimize the kthSmallest routine?
*/


//Approach 1:
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
      int count = countNodes(root.left);
      if (k <= count) {
          return kthSmallest(root.left, k);
      } else if (k > count + 1) {
          return kthSmallest(root.right, k-1-count); // 1 is counted as current node
      }
      
      return root.val;
  }
  
  public int countNodes(TreeNode n) {
      if (n == null) return 0;
      
      return 1 + countNodes(n.left) + countNodes(n.right);
  }
}


//Approach 2:
How to traverse the tree
There are two general strategies to traverse a tree:

Depth First Search (DFS):
In this strategy, we adopt the depth as the priority, so that one would start from a root and 
reach all the way down to certain leaf, and then back to root to reach another branch.
The DFS strategy can further be distinguished as preorder, inorder, and postorder depending
on the relative order among the root node, left node and right node.

Breadth First Search (BFS):
We scan through the tree level by level, following the order of height, from top to bottom.
The nodes on higher level would be visited before the ones with lower levels.
On the following figure the nodes are numerated in the order you visit them, please follow 1-2-3-4-5 to compare different strategies.

postorder

To solve the problem, one could use the property of BST : inorder traversal of BST is an array sorted in the ascending order.

Approach 1: Recursion
It's a very straightforward approach with \mathcal{O}(N)O(N) time complexity.
The idea is to build an inorder traversal of BST which is an array sorted in the ascending order.
Now the answer is the k - 1th element of this array.

Complexity Analysis:
Time complexity : \mathcal{O}(N)O(N) to build a traversal.
Space complexity : \mathcal{O}(N)O(N) to keep an inorder traversal.



class Solution {
  public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {
    if (root == null) return arr;
    inorder(root.left, arr);
    arr.add(root.val);
    inorder(root.right, arr);
    return arr;
  }

  public int kthSmallest(TreeNode root, int k) {
    ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());
    return nums.get(k - 1);
  }
}



Approach 2: Iteration
The above recursion could be converted into iteration, with the help of stack.
This way one could speed up the solution because there is no need to build the entire inorder traversal,
and one could stop after the kth element.



class Solution {
  public int kthSmallest(TreeNode root, int k) {
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

    while (true) {
      while (root != null) {
        stack.add(root);
        root = root.left;
      }
      root = stack.removeLast();
      if (--k == 0) return root.val;
      root = root.right;
    }
  }
}

Complexity Analysis

Time complexity : \mathcal{O}(H + k)O(H+k), where HH is a tree height. This complexity is defined by the stack, which contains at least H + kH+k elements, since before starting to pop out one has to go down to a leaf. This results in \mathcal{O}(\log N + k)O(logN+k) for the balanced tree and \mathcal{O}(N + k)O(N+k) for completely unbalanced tree with all the nodes in the left subtree.
Space complexity : \mathcal{O}(H + k)O(H+k), the same as for time complexity, \mathcal{O}(N + k)O(N+k) in the worst case, and \mathcal{O}(\log N + k)O(logN+k) in the average case.

