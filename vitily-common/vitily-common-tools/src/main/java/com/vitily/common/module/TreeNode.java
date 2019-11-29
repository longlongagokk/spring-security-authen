package com.vitily.common.module;

import java.io.Serializable;
import java.util.List;

/**
 * 树
 * @author lether
 *
 */
public class TreeNode implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 当前节点文本
	 */
	private String text;
	/**
	 * 当前节点值
	 */
	private String value;
	/**
	 * 文本链接
	 */
	private String navigateUrl;
	/**
	 * 文本提示
	 */
	private String toolTip;
	/**
	 * 是否展开
	 */
	private boolean expand=true;
	/**
	 * 是否选中
	 */
	private boolean checked;
	/**
	 * 是否显示checkbox
	 */
	private boolean showCheckBox=true;
	/**
	 * 事件
	 */
	private String evt;
	/**
	 * 子节点
	 */
	private List<TreeNode> children;

	public String getText() {
		return text;
	}

	public TreeNode setText(String text) {
		this.text = text;
		return this;
	}

	public String getValue() {
		return value;
	}

	public TreeNode setValue(String value) {
		this.value = value;
		return this;
	}

	public String getNavigateUrl() {
		return navigateUrl;
	}

	public TreeNode setNavigateUrl(String navigateUrl) {
		this.navigateUrl = navigateUrl;
		return this;
	}

	public String getToolTip() {
		return toolTip;
	}

	public TreeNode setToolTip(String toolTip) {
		this.toolTip = toolTip;
		return this;
	}

	public boolean isExpand() {
		return expand;
	}

	public TreeNode setExpand(boolean expand) {
		this.expand = expand;
		return this;
	}

	public boolean isChecked() {
		return checked;
	}

	public TreeNode setChecked(boolean checked) {
		this.checked = checked;
		return this;
	}

	public boolean isShowCheckBox() {
		return showCheckBox;
	}

	public TreeNode setShowCheckBox(boolean showCheckBox) {
		this.showCheckBox = showCheckBox;
		return this;
	}

	public String getEvt() {
		return evt;
	}

	public TreeNode setEvt(String evt) {
		this.evt = evt;
		return this;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public TreeNode setChildren(List<TreeNode> children) {
		this.children = children;
		return this;
	}
}
