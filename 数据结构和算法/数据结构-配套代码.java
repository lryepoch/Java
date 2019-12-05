【栈】

实现方式1：使用一个队列实现，可以使用LinkedList或者ArrayDeque实现，主要是实现其常用的push、pop以及peek方法。
	class MyStack<T>{
		private Deque<Integer> stack = new LinkedList<Integer>();

		public void push(Integer element){
			stack.addFirst(element);
		}

		public Integer pop(Integer element){
			return stack.removeFirst();
		}

		public Integer peek(){
			return stack.getFirst();
		}

		public String toString(){
			return stack.toString();
		}
	}

	实现方式2：使用int数组来实现
	class MyStack<T>{
		//栈的最大深度
		protected int MAX_DEPTH = 10;
		//当前深度
		protected int depth;
		//实际的栈
		protected int[] stack = new int[MAX_DEPTH];

		protected void push(int element){
			if(depth==MAX_DEPTH-1)
					throw new RuntionException("栈已满，无法添加元素");
			stack[depth++]=element;
		}

		protected int pop(){
			if(depth==0)
					throw new RuntimeException("当前栈为空，无法删除元素")；
			int result = stack[--depth];
			stack[depth]=0;
			return result;
		}

		protected int peek(){
			if(depth==0)
				throw new RuntimeException("当前栈为空，无法删除元素")；
			return stack[depth-1];
		}

		public String toString(){
			return stack.toString();
		}
	}

	实现方式3：用2个队列实现栈；


	-----------------------------------------------------------------------------------
	【二叉树】

	前序遍历：
1.递归解法：如果二叉树为空，空操作；如果二叉树不为空，访问根节点，前序遍历左子树，前序遍历右子树；
	class Solution{
		public static void preorderTraversal(TreeNode root){
			if(root==null)
				return;
			System.out.println(root.val+" ");
			preorderTraversal(root.left);
			preorderTraversal(root.right)；
		}
	}

2.迭代解法：用一个辅助stack，总是把右孩子放进栈；
	class Solution{
		public static void preorderTraversal(TreeNode root){
			if (root==null) {
				return;
			Stack<TreeNode> stack = new Stack<TreeNode>();
			stack.push(root);
			if(!stack.isEmpty()){
				TreeNode cur = stack.pop();
				System.out.println(cur.val+" ");
				if(cur.right!=null)
						stack.push(cur.right);
				if(cur.left!=null)
					stack.push(cur.left);
			}
		}
	}


	中序遍历：
1.递归解法：
class Solution{
	public static void inorderTraversal(TreeNode root){
		if(root==null)
			return;
		inorderTraversal(root.left);
		System.out.println(root.val+" ");
		inorderTraversal(root.right);
	}
}

2.迭代解法：
	class Solution{
		public static void inorderTraversal(TreeNode root){
			if(root==null)
				return;
			Stack<TreeNode> stack=new Stack<TreeNode>();
			
		}
	}
	

	后序遍历：
1.递归解法：
 class Solution{
 	public static void postorderTraversal(TreeNode root){
 		if(root==null)
 			return;
 		postorderTraversal(root.left);
 		postorderTraversal(root.right);
 		System.out.println(root.val+" ");

 	}
 }