【线性表】
【树表】
【散列表】

线性表
--------------------------

//顺序查找
//如果找到，返回数组下标
//时间复杂度O(n)
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
//时间复杂度O(logn)
//空间复杂度O()

**递归实现**
//使用递归时，方法参数需要携带诸如 left，right；start，end；low，high；
public int Search_Bin(int[] a, int left, int right, int target){
	int mid = (left + right)/2;
	int midValue = a[mid];
	if (left<=right) {
			if (midValue>target) {
				return Search_Bin(a, left, mid-1, target);
			}else if(midValue<target){
				return Search_Bin(a, mid+1, right, target);
			}else
				return mid;
		}
	return -1;
}


**非递归实现**
class Solution{
	public int Search_Bin(int[] a,int key){
		int low=0;
		int high=a.length-1;
		while(low<=high){
			int mid=(low+high)/2;
			if(a[mid]>key)
				high=mid-1;
			else if(a[mid]<key)
				low=mid+1;
			else return mid;
		}
		return -1;
	}
}








树表
----------------------







散列表
---------------------