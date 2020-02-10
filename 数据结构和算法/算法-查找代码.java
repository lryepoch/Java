【线性表】
【树表】
【散列表】
--------------------------
线性表的查找：
//顺序查找
//如果找到，返回数组下标；
//时间复杂度O(n),

class Solution{
	public int seqSearch(int[] a,int key){
		for (int i=0; i<a.length; i++){
			if (a[i]==key)
				return i;
		}
		return -1;
	}
	public static void main(String args[]){

		int a[]={5,6,8,2,4,0};
		Solution s = new Solution();
		int k = s.SeqSearch(a,0);
		System.out.println("下标="+k);
	}
}

//折半查找, 又叫二分查找。
//前提条件是需要有序表顺序存储;对于静态查找表，一次排序后不再变化，折半查找能得到不错的效率。但对于需要频繁执行插入或删除操作的数据集来说，维护有序的排序会带来不小的工作量，那就不建议使用。
//适用于不经常变动而查找频繁的有序列表；
//时间复杂度是O(logn)

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
	public static void main(String args[]){

		int a[]={1,3,4,5,7,9};
		Solution s = new Solution();
		int k = s.Search_Bin(a,7);
		System.out.println("下标="+k);

	}
}


1.在等概率下，查找成功时的平均查找长度相同，查找失败时的平均查找长度不相同。


----------------------
树表的查找：





---------------------
散列表的查找：