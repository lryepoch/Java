【线性表】静态查找算法（折半查找、插值查找、斐波那契查找、分块查找）
【树表】动态查找算法（二叉排序树、平衡二叉树、B-树、B+树）
【散列表】

线性表
--------------------------

//顺序查找
//时间复杂度O(n)，遍历数组
//空间复杂度O(1)
class Solution{
	public int seqSearch(int[] a,int key){
		for (int i=0; i<a.length; i++){
			if (a[i]==key)
				return i;
		}
		return -1;
	}
}

//折半查找, 又叫二分查找。
//前提条件：需要有序表顺序存储;
//适用于不经常变动而查找频繁的有序列表；
/**总共有n个元素，渐渐跟下去就是n,n/2,n/4,....n/2^k（接下来操作元素的剩余个数），其中k就是循环的次数
	由于你n/2^k取整后>=1，即令n/2^k=1
	所以，时间复杂度O(logn)
*/
//空间复杂度O(1)

**递归实现**
//使用递归时，方法参数需要携带诸如 left，right；start，end；low，high；
class Solution{
	public int search_Bin(int[] a, int left, int right, int target){
		int mid = (left + right)/2;
		int midValue = a[mid];
		if (left<=right) {			//用if不用while
			if (midValue>target) {
				return Search_Bin(a, left, mid-1, target);
			}else if(midValue<target){
				return Search_Bin(a, mid+1, right, target);
			}else {
				return mid;
			}
		}
		return -1;
	}
}


**非递归实现**
class Solution{
	public int search_Bin(int[] a,int key){
		int low=0;
		int high=a.length-1;
		while(low<=high){		//用while不用if
			int mid=(low+high)/2;
			if(a[mid]>key)
				high=mid-1;
			else (a[mid]<key) {
				low=mid+1;
			}
			else return mid;
		}
		return -1;
	}
}








树表
----------------------
二叉排序树(BST)
平衡二叉树(AVL)
//前序遍历
//中序遍历
//后序遍历
//层序遍历






散列表
---------------------
散列函数的构造方法
直接定址法
数字分析法
平方取中法
折叠法
除留余数法
随机数法

处理散列冲突的方法
开放定址法
再散列函数法
链地址法
公共溢出区法