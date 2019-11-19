package cn.im.dsaa;

import java.util.List;

public class CirclarQueue {
	
	private int[] data;
	private int head;
	private int tail;
	
	public CirclarQueue(int length) {
		data = new int[length];
		head = tail = -1;
	}
	
	public boolean enQueue(int value) {
		if(!isFull()) {
			tail ++;
			if(tail >= data.length) {
				tail = 0;
			}
			data[tail] = value;
			if(head == -1) head = 0;
			return true;
		}
		return false;
	}
	
	public boolean deQueue() {
		if(!isEmpty()) {
			data[head] = 0;
			head ++;
			if(head >= data.length) {
				head = 0;
			}
			return true;
		}
		return false;
	}
	
	public boolean isFull() {
		if(head < tail && tail - head == data.length-1) {
			return true;
		}
		if(head > tail &&  head - tail == 1 && data[head] != 0) {
			return true;
		}
		return false;
	}
	
	public boolean isEmpty() {
		if(head == tail && head == -1) return true;
		if(head > tail &&  head - tail == 1 && data[head] == 0) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		CirclarQueue queue = new CirclarQueue(3);
		System.out.println(queue.enQueue(1));
		System.out.println(queue.enQueue(2));
		System.out.println(queue.enQueue(3));
		System.out.println(queue.enQueue(4));
	}
}
