package com.pugfish1992.fabre.data.task;

import android.support.annotation.NonNull;

import com.pugfish1992.fabre.data.Diff;
import com.pugfish1992.fabre.data.comment.Comment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by daichi on 11/30/17.
 */

public class TaskDiff implements Diff {

    private String mNewName;
    private Comment mNewPrimaryComment;
    private List<Comment> mWillBeRemovedComments;
    private List<Comment> mWillBeAddedComments;

    private boolean mIsSetNewName = false;
    private boolean mIsSetNewPrimaryComment = false;

    public TaskDiff() {
        mWillBeRemovedComments = new ArrayList<>();
        mWillBeAddedComments = new ArrayList<>();
    }

    public TaskDiff newName(String newName) {
        mNewName = newName;
        mIsSetNewName = true;
        return this;
    }

    public TaskDiff newPrimaryComment(@NonNull Comment newPrimaryComment) {
        mNewPrimaryComment = newPrimaryComment;
        mIsSetNewPrimaryComment = true;
        return this;
    }

    public TaskDiff addComments(@NonNull Comment... comments) {
        mWillBeAddedComments.addAll(Arrays.asList(comments));
        return this;
    }

    public TaskDiff removeComments(@NonNull Comment... comments) {
        mWillBeRemovedComments.addAll(Arrays.asList(comments));
        return this;
    }

    /* Intentional package-private visibility */
    @NonNull
    Task apply(@NonNull Task source) {
        String name = (mIsSetNewName) ? mNewName : source.name();

        Comment primaryComment= (mIsSetNewPrimaryComment)
                ? mNewPrimaryComment : source.primaryComment();

        List<Comment> comments = new ArrayList<>(source.comments());
        comments.removeAll(mWillBeRemovedComments);
        comments.addAll(mWillBeAddedComments);

        return new TaskImpl(
                source.id(),
                name,
                primaryComment,
                comments);
    }
}
