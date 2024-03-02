<h2><a href="https://leetcode.com/problems/minimum-operations-to-make-numbers-non-positive/">2702. Minimum Operations to Make Numbers Non-positive</a></h2><h3>Hard</h3><hr><div><p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and two integers <code>x</code> and <code>y</code>. In one operation, you must choose an index <code>i</code> such that <code>0 &lt;= i &lt; nums.length</code> and perform the following:</p>

<ul>
	<li>Decrement <code>nums[i]</code> by <code>x</code>.</li>
	<li>Decrement values by <code>y</code> at all indices except the <code>i<sup>th</sup></code> one.</li>
</ul>

<p>Return <em>the minimum number of operations to make all the integers in </em><code>nums</code> <em><strong>less than or equal to zero.</strong></em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre><strong>Input:</strong> nums = [3,4,1,7,6], x = 4, y = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> You will need three operations. One of the optimal sequence of operations is:
Operation 1: Choose i = 3. Then, nums = [1,2,-1,3,4]. 
Operation 2: Choose i = 3. Then, nums = [-1,0,-3,-1,2].
Operation 3: Choose i = 4. Then, nums = [-3,-2,-5,-3,-2].
Now, all the numbers in nums are non-positive. Therefore, we return 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre><strong>Input:</strong> nums = [1,2,1], x = 2, y = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can perform the operation once on i = 1. Then, nums becomes [0,0,0]. All the positive numbers are removed, and therefore, we return 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= y &lt; x &lt;= 10<sup>9</sup></code></li>
</ul>
</div>