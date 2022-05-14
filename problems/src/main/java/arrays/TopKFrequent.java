package arrays;

import java.util.*;

public class TopKFrequent {

    public static void main(String[] args) {
        // ["KthLargest","add","add","add","add","add"]
        //[[3,[4,5,8,2]],[3],[5],[10],[9],[4]]
        KthLargest kthLargest = new KthLargest(3, new int[]{4,5,8,2});
        int res = kthLargest.add(3);
        res = kthLargest.add(5);
        res = kthLargest.add(10);
        res = kthLargest.add(9);
        res = kthLargest.add(4);
    }

    // https://leetcode.com/problems/kth-largest-element-in-a-stream/
    static class KthLargest {
        PriorityQueue<Integer> pq;
        int k;

        public KthLargest(int k, int[] nums) {
            HashMap m;

            this.pq = new PriorityQueue<>(k);
            this.k  = k;
            for(int i: nums){
                pq.add(i);
                if (pq.size()>k){
                    pq.poll();
                }
            }
        }

        public int add(int val) {
            pq.add(val);
            if (pq.size()>this.k){
                pq.poll();
            }
            return pq.peek();
        }
    }

    //https://leetcode.com/problems/top-k-frequent-elements/
    public int[] topKFrequent(int[] nums, int k) {

        int[] res = new int[k];

        // 1. build freq
        // 0(N)
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }

        // build heap
        //NOTE since java api does not provide constructor for comparator and collection
        // done separately
        // however: heap construction directly via heapify can be done O(N)
        // here we do it with add which takes logn -> nlogk + klogk + n + klogk -> nlogk
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()
        );

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k)
                pq.poll();
        }

        int i = 0;
        while (!pq.isEmpty()) {
            res[i] = pq.poll().getKey();
            i++;
        }

        return res;
    }


    // bucket sort


    private List<Integer>[] bucketSort(Map<Integer, Integer> freq, int max) {
        List<Integer>[] buckets = new LinkedList[max];
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int frequency = entry.getValue();
            int index = max - frequency;
            if (buckets[index] == null) {
                buckets[index] = new LinkedList<>();
            }
            buckets[index].add(entry.getKey());
        }
        return buckets;
    }

    public int[] topKFrequentBucket(int[] nums, int k) {

        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }

        List<Integer>[] buckets = bucketSort(freq, nums.length);

        int[] ans = new int[k];
        int i = 0;

        outer:
        for (List<Integer> bucket : buckets) {
            if (bucket == null) continue;
            for (int num : bucket) {
                ans[i++] = num;
                if (i == k) break outer;
            }
        }
        return ans;
    }

    // quickselect solution

    int[] unique;
    Map<Integer, Integer> count;

    public void swap(int a, int b) {
        int tmp = unique[a];
        unique[a] = unique[b];
        unique[b] = tmp;
    }

    public int partition(int left, int right, int pivot_index) {
        int pivot_frequency = count.get(unique[pivot_index]);
        // 1. move pivot to end
        swap(pivot_index, right);
        int store_index = left;

        // 2. move all less frequent elements to the left
        for (int i = left; i <= right; i++) {
            if (count.get(unique[i]) < pivot_frequency) {
                swap(store_index, i);
                store_index++;
            }
        }

        // 3. move pivot to its final place
        swap(store_index, right);

        return store_index;
    }

    public void quickselect(int left, int right, int k_smallest) {
        /*
        Sort a list within left..right till kth less frequent element
        takes its place.
        */

        // base case: the list contains only one element
        if (left == right) return;

        // select a random pivot_index
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left);

        // find the pivot position in a sorted list
        pivot_index = partition(left, right, pivot_index);

        // if the pivot is in its final sorted position
        if (k_smallest == pivot_index) {
            return;
        } else if (k_smallest < pivot_index) {
            // go left
            quickselect(left, pivot_index - 1, k_smallest);
        } else {
            // go right
            quickselect(pivot_index + 1, right, k_smallest);
        }
    }

    public int[] topKFrequentQuickselect(int[] nums, int k) {
        // build hash map : character and how often it appears
        count = new HashMap();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // array of unique elements
        int n = count.size();
        unique = new int[n];
        int i = 0;
        for (int num : count.keySet()) {
            unique[i] = num;
            i++;
        }

        // kth top frequent element is (n - k)th less frequent.
        // Do a partial sort: from less frequent to the most frequent, till
        // (n - k)th less frequent element takes its place (n - k) in a sorted array.
        // All element on the left are less frequent.
        // All the elements on the right are more frequent.
        quickselect(0, n - 1, n - k);
        // Return top k frequent elements
        return Arrays.copyOfRange(unique, n - k, n);
    }


}
