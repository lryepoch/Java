
排序算法稳定性：保证2个相等的数排序前和排序后它们两个的前后位置顺序相同。在简单形式化一下，如果Ai = Aj，Ai原来在位置前，排序后Ai还是要在Aj位置前。


插入排序：直接插入排序和希尔排序
-----------------
//直接插入排序：第一个记录当成有序序列，第二个到最后一个记录依次跟有序序列中的元素比较，然后插入。最后得到一个有序序列。
//一种稳定排序
//时间复杂度O(n^2)，最坏情况是比较i次，移动i+1次。
//空间复杂度O(1),因为需要一个哨兵;【将数组第一个位置设置为哨兵】
//适用于顺序和链式存储结构，只是在单链表上无需移动记录，只要修改指针就好。
//更适用于初始记录基本有序，n较小；当无序，n较大时，该算法时间复杂度高，不宜采用。

class Solution {
	public void insertSort(int[] a) {

		for(int i=1;i<a.length;i++) {
			if(a[i]<a[i-1]) {
				//若第i个元素大于i-1元素，直接插入。小于的话，移动有序表后插入
				int temp=a[i];				 //复制为哨兵，即存储待排序元素
			}
			for(int j=i-1;j>=0 && a[j]>temp;j--){	//从后向前，在有序表中寻找插入位置
				a[j+1]=a[j];				//元素后移
			}
			a[j+1]=temp;					//插入到正确位置
		}
	}
}


//希尔排序：先计算增量dk，再执行直接插入排序的变异体，再计算增量dk，再执行变异体，再计算增量dk……直到dk=1。
//即分组插入，相隔距离为dk的元素成为一组；
//缩小增量的排序;
//时间复杂度O(nlogn)【分组，每次减少一半】
//空间复杂度O(1)
//不稳定排序
//只能顺序存储结构
//适合初始记录无序，n较大。事实上是，基于“减少记录个数”（先分组插入）和“序列基本有序”（最后当dk=1时，相当于对全体进行一次直接插入排序）而改进。
//间隔某个增量dk而分组，一般取分割数组的一半。

public class Solution {
	/** 
	 * 直接插入排序的一般形式 
	 * @param int dk 缩小增量，如果是直接插入排序，dk=1 
	 */  
	public void shellSort(int[] a,int dk) {
		for(int i=dk;i<a.length;i++){

			//若第i个元素大于i-dk元素，直接插入。小于的话，移动有序表后插入  
			if(a[i]<a[i-dk])
				int temp=a[i];		 //复制为哨兵，即存储待排序元素  

			for(int j=i-dk;j>=0 && a[j]>temp;j=j-dk)	//从后向前查找在有序表的插入位置  
				a[j+dk]=a[j];		//元素后移
			a[j+dk]=temp;			//插入到正确位置  
		}
	}
	/** 
	 * 先按增量dk（n/2,n为要排序数的个数进行希尔排序 ）
	 * 
	 */ 
	public void shellDK(int[] a){
		int dk=(a.length-1)/2;
		while(dk>=1){
			shellSort(a,dk);
			dk=dk/2;
		}
	}
}




选择排序：堆排序 和 简单选择排序
-----------------
//堆排序
/**算法描述：对于给定的n个记录，初始时把这些记录看作一棵顺序存储的二叉树，然后将其调整为一个大顶堆，然后将堆的最后一个元素与堆顶元素进行交换后，堆的最后一个元素即为最大记录；接着将前（n-1）个元素重新调整为一个大
顶堆，再将堆顶元素与当前堆的最后一个元素进行交换后得到次大的记录，重复该过程直到调整的堆中只剩下一个元素时为止。
*/
/*实现堆排序需要实现两个问题：
	1.建初堆：如何将一个无序序列建成一个堆；【大根堆或者小根堆】
	2.调整堆：交换堆顶元素，在堆顶元素改变之后，如何调整剩余元素成为一个新的堆？
		因为建初堆要用到调整堆，所以先完成调整堆的操作。
*/

//不稳定排序
//时间复杂度O(nlogn)
//空间复杂度为O(1)，交换堆顶元素H[0]和堆中最后一个元素；
//只能用于顺序存储结构；
//记录较少不宜采用；
//每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；或者每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。
			
//这里例子是大根堆的演示
/** 
 *调整堆
 * 建堆渗透函数
 * 已知H[s…m]除了H[s] 外均满足堆的定义 
 * 调整H[s],使其成为大顶堆.即将对第s个结点为根的子树筛选,  
 * 堆调整只是完成了一次操作而已；
 * 
 * @param H是待调整的堆数组 
 * @param s是待调整的数组元素的位置 
 * @param length是数组的长度 
 */  
class Solution{
		public void heapAdjust(int H[], int s, int length)
		{

			//s在这里是指从当前位置开始进行堆调整
			int temp=H[s];

			//当前调整结点的左孩子结点的位置【s代表数组下标所以从0开始，child+1 为当前调整结点的右孩子结点的位置】
			int child=2*s+1; 

			while(child<length)
			{
				if(child+1<length && H[child]<H[child+1])  //如果右孩子大于左孩子
					child++;

				if(H[child]>H[s])      //较大子结点大于父结点
				{
					H[s]=H[child];      //较大子结点往上移动，替换父结点
					s=child;        		//重新设置父结点，即找到下一个要调整的结点
					child=2*s+1;      //重新寻找左孩子

				}
				else break;   // 如果当前待调整结点大于它的左右孩子，则不需要调整，直接退出 
					
				H[s]=temp;       // 之前待调整的父结点放到原来比其大的孩子结点上 [一个交换位置操作]
			}		
		}
		/** 
		 *建初堆，就从最后一个有孩子结点的父结点开始初始化建堆
		 * 初始堆进行调整 
		 * 将H[0..length-1]建成堆 
		 * 调整完之后第一个元素是序列的最小的元素 
		 */  

		public void buildingHeap(int H[])
		{
			//从最后一个有孩子结点的根结点（i=length/2-1）开始初始化堆
			for(int i=a.length/2-1; i>=0; i--)
				heapAdjust(H ,i , length);
		}
		/** 
		 * 堆排序算法 
		 */  
		public void heapSort(int H[])
		{
			//初始化堆
			buildingHeap(H);

			//从最后一个叶子结点开始，跟根结点交换位置
			for(int i=a.length-1;i>0;i--)
			{

				//交换堆顶元素H[0]和堆中最后一个元素 
				int temp=H[0];
				H[0]=H[i];
				H[i]=temp;

		        //每次交换堆顶元素和堆中最后一个元素之后，都要对堆进行调整  ,以满足堆的定义
				heapAdjust(H, 0, i-1);
			}
		}
}


/**简单选择排序：选择第一个元素跟后面所有元素比较，其结果是最小元素位于第一；然后选择第二个跟后面剩余所有元素比较，其结果是次小
                 元素位于第二；以此类推，直到比较的纪录只有一个为止，完成排序。即给每个位置选择当下最小的元素。
*/
//不稳定排序
//时间复杂度O(n^2)		【2个for循环】
//空间复杂度O(1)			【需要一个过渡变量】
//用于链式存储结构
//改进从减少比较出发
class Solution{
	public void selectSort(int[] a){

		 //先固定第j个位置，j从1开始，即第一个位置开始
		for (int i=0;i<a.length-1 ;i++ ) 

			for(int j=i+1;j<a.length;j++)
			{
				if(a[i]>a[j]){			 //最小元素与第i个元素交换值
					int x=a[i];
					a[i]=a[j];
					a[j]=x;
				}
			}
	}
}




交换排序：快速排序和冒泡排序
-----------------
/**快速排序：取枢轴（第一个记录），经一趟排序，记录小于枢轴的，交换到前面；记录大于枢轴的，交换到后面。结果将待排序记录分成两个子表，最后枢轴位置在分界处的位置。
	分别对左右子表重复上述过程，直至每一个子表只有一个记录时[也就是low=high时，退出循环]，排序完成。
*/
//完成一趟排序，low和high的位置相等，即为枢轴在此趟过程的最终位置，原表被分为两个子表。在一趟排序过程中，枢轴记录是固定的。
//不稳定排序
//时间复杂度O(nlogn)		【待排序记录每次分成两半，递归的栈深度就为 [log2n]+1; 对整个数组扫描一遍，做n次比较】
//空间复杂度最好O(logn)		【主要是递归造成的栈空间的使用】
//只适用顺序存储结构
//适用于初始无序，[n较大时(是所有内部排序中速度最快的)].
class Solution{
	public void qsort(int[] a,int low,int high){		//递归对子表进行排序
		if(low<high){									//不需要=，当=时只剩一个记录
			int x=partition(a,low,high);				//将表一分为二，返回枢轴位置
			qsort(a,low,x-1);							//递归对底子表进行排序
			qsort(a,x+1,high);							//递归对高子表进行排序
		}
	}

	public int partition(int[] a,int low,int high){		//一趟排序，结束后分成两个子表

		int x=a[low];					//设置枢纽元素
		while(low<high){				//从表的两端交替向中间扫描，先从右到左
			
			while(low<high&&a[high]>=x) {
				high--;			//从high 所指位置向前搜索，至多到low+1 位置。将比枢纽元素小的交换到低端
			}	  
			a[low]=a[high];

			while(low<high&&a[low]<=x) {
				low++;		//将比枢纽元素大的交换到高端
			}
			a[high]=a[low];
		}
		a[low]=x;			//还回给你
		return low;			//返回分界点的位置, 此时low==high
	}
}


//冒泡排序【从前向后冒泡，最大值沉到最后；完了再次从前往后，重复冒泡】
//概念：对于n个记录，从第1个开始，依次对相邻两个元素进行比较，前一个大于后一个，则交换位置。一轮过后，最大记录沉到最后。对后n-1个记录重复上述操作，直到只剩一个记录为止。
//时间复杂度O(n^2）
//空间复杂度O(1)
//稳定排序
//用于链式存储结构
//初始无序，n较大时不宜采用

class Solution{
	public void bubbleSort(int[] a){
		for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]>nums[j]){
                    int temp = nums[i];
                    nums[i]=nums[j];
                    nums[j]=temp;
                }
            }
		}
	}
}

//改进版冒泡排序，先后从前面和后面向相反方向冒泡，提高了效率。
//缩小范围
//时间复杂度O(n)
//空间复杂度O(1)
class Solution{
	public void bubbleSort(int[] a){
		int low=0;
		int high=a.length-1;

		while(low<high){						//不用等于，当等于时，已经没有必要交换了
			for(int i=low;i<high;i++){		 //正向冒泡，找最大者。一遍下来，最大沉到底部，消去这个最大。
				if (a[i]>a[i+1]) {
					int x=a[i];
					a[i]=a[i+1];
					a[i+1]=x;
				}
			}
			high--;//最后一个已排序
			for (int j=high;j>low ;j-- ) {		//反向冒泡，找最小者。一遍下来，最小升到顶部，消去这个最小。
				if(a[j]<a[j-1]){
					int y=a[j];
					a[j]=a[j-1];
					a[j-1]=y;
				}
				
			}
			low++;//最前一个已排序
		}
	}
}





归并排序
------------------
//基于分治法，将一个无序序列，先分成一个个待排序的子序列，然后将相邻两个子序列进行合并，即治之。最终合并成一个有序序列。
//2-路归并排序核心：待排序序列前后两个相邻的有序序列归并成一个有序序列。
//稳定排序
//时间复杂度O(nlogn)
//空间复杂度O(n)，因为需要一个过渡数组
//用于链式不需要辅助空间但要递归工作栈,用顺序需要和待排序记录相同的辅助空间

public class Merge {
	public void merge(int[] a, int start, int mid, int end) {
		//借助一个过渡数组
		int[] b = new int[a.length];
		
		int i = start;		//a
		int j = mid + 1;	//a
		int k = start;		//b
		
		System.out.println("merge:"+start+"~"+end);
		while (i <= mid && j <= end) {		//将a中记录由小到大并入b中。
			if (a[i] < a[j])
				b[k++] = a[i++];		
			else
				b[k++] = a[j++];		
		}
		while (i <= mid)
			b[k++] = a[i++];		//将剩余的a[start…mid]复制到b中
		while (j <= end)
			b[k++] = a[j++];		//将剩余的a[mid+1…end]复制到b中

		for (int n = start; n <= end; n++) {	 //排好序的b归还给a
			a[n] = b[n];
		}
		for (int m : a) {
			System.out.print(m+" ");
		}
		System.out.println();
		System.out.println("--------------------------------");
	}

	//内部使用递归
	public void mergeSort(int[] a, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;		         //将当前序列一分为二，求出分裂点mid
			mergeSort(a, start, mid);		// 左边有序	 //递归归并排序……a[low…mid]
			mergeSort(a, mid + 1, end);		// 右边有序	 //递归归并排序……a[mid+1…high]
			
			 //不断在原来的基础上，将a[start...mid]和a[mid+1...end]归并到b[start...end]
			merge(a, start, mid, end);			//最后整合在一起
		}
	}

	public static void main(String[] args) {
		Merge mergeSort = new Merge();
		int[] a = { 9, 5, 8, 1, 0, 5, 7 };

		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
		mergeSort.mergeSort(a, 0, a.length - 1);
	}
}





基数排序
--------------------
//稳定排序；
//可用于顺序存储和链式存储结构
//时间复杂度O(d(n+rd))
//空间复杂度O(n+rd)
//
//算法思想：基数排序又称为“桶子法”，从低（高）位开始将待排序的数按照这一位的值放到相应的编号为0~9的10个桶中。等到低（高）位排完得到一个子序列；
//				再将这个序列按照次低（高）位的大小进入相应的桶中，一直排到最高（低）位为止，数组排序完成。
//分类：LSD——从低位向高位排，MSD——从高位向低位排。
/**算法执行步骤：
				(1)遍历序列找出最大的数(为的是确定最大的数是几位数)；
				(2)计算最大数字的位数；
				(3)用一个tong数组计算原数组中某一位(从最低位向最高位计算)相同数据出现的位置；
				(4)用一个num数组统计原数组中某一位(从低位向高位统计)相同的数据出现的次数；
				(5)将10个桶中的数据从小到大重新用数组A收集起来；

				(6)重复(3)(4)(5)直到所有位都被统计并计算过，用数组A收集起来；
				(7)最后数组A就是最终结果了；
*/
//万能公式：a/k%10：
【对a求个位数：除以k=1,对10取模；对a求十位数：除以k=10,对10取模；对a求百位数：除以k=100,对10取模】

//以下是LSD：
public class RadixSort {
	public void radixSort(int[] A, int n) {
		
		//遍历序列找出最大的数(为的是确定最大的数是几位数)；
		int max = A[0];				//先假设第一个数最大
		for (int i = 1; i < n; i++) {
			if (max < A[i])
				max = A[i];
		}
		double d = Math.pow(10, String.valueOf(max).length());	  //计算最大数的位数

		int k = 1;												//代表位数对应的数：1,10,100...
		int[][] tong = new int[10][n]; 				//桶，排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
		int[] num = new int[n];						 // 记录每个桶中存入数的个数，最多有n个记录位于同一个桶
		
		//遍历完所有位上的数字
		while (k < d) {
			
			//将数组array里的每个数字放在相应的桶里，首先根据个位，然后根据十位，最后根据百位
			for (int a : A) {	
				int m = (a / k) % 10;
				tong[m][num[m]] = a;
				//System.out.println("num[m]"+num[m]);
				num[m]++;
			}
			
			int c = 0;//保存每一位上排序后的结果用于下一位的排序输入
			
			//将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
			for (int i = 0; i < n; i++) {
				
				//这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
				if (num[i] != 0) {
					for (int j = 0; j < num[i]; j++) {
						A[c] = tong[i][j];
						c++;
					}
				}
				num[i] = 0;	//将对应的桶里计数器置0，用于下一个位的排序
			}
			k = k * 10;		//进化到更高位
		}
	}

	public static void main(String[] args) {

		int[] A = { 12, 1, 23, 123, 222, 65, 34 };
		RadixSort r = new RadixSort();

		r.radixSort(A, 7);
		for (int a : A) {
			System.out.print(a + " ");
		}
	}
}




