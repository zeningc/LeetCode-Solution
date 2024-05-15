<h2><a href="https://leetcode.com/problems/kth-smallest-subarray-sum/">1918. Kth Smallest Subarray Sum</a></h2><h3>Medium</h3><hr><div><p>Given an integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>, return <em>the </em><code>k<sup>th</sup></code> <em><strong>smallest subarray sum</strong>.</em></p>

<p>A <strong>subarray</strong> is defined as a <strong>non-empty</strong> contiguous sequence of elements in an array. A <strong>subarray sum</strong> is the sum of all elements in the subarray.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre><strong>Input:</strong> nums = [2,1,3], k = 4
<strong>Output:</strong> 3
<strong>Explanation: </strong>The subarrays of [2,1,3] are:
- [2] with sum 2
- [1] with sum 1
- [3] with sum 3
- [2,1] with sum 3
- [1,3] with sum 4
- [2,1,3] with sum 6 
Ordering the sums from smallest to largest gives 1, 2, 3, <u>3</u>, 4, 6. The 4th smallest is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre><strong>Input:</strong> nums = [3,3,5,5], k = 7
<strong>Output:</strong> 10
<strong>Explanation: </strong>The subarrays of [3,3,5,5] are:
- [3] with sum 3
- [3] with sum 3
- [5] with sum 5
- [5] with sum 5
- [3,3] with sum 6
- [3,5] with sum 8
- [5,5] with sum 10
- [3,3,5], with sum 11
- [3,5,5] with sum 13
- [3,3,5,5] with sum 16
Ordering the sums from smallest to largest gives 3, 3, 5, 5, 6, 8, <u>10</u>, 11, 13, 16. The 7th smallest is 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n&nbsp;&lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= n * (n + 1) / 2</code></li>
</ul>
</div>