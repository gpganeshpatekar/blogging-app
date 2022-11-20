package com.demo.blogging.services;

import com.demo.blogging.payloads.SubCommentDto;

public interface SubCommentService {
	
//	add sub comment
	SubCommentDto replyToComment(SubCommentDto subCommentDto,String username,Integer postId, Integer commentId);
	
//	delete sub comment
	void deleteReplyOfComment(Integer subCommentId);

}
