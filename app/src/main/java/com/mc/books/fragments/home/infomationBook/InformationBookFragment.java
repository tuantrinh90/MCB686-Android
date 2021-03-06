package com.mc.books.fragments.home.infomationBook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.bon.customview.textview.ExtTextView;
import com.mc.adapter.AuthorAdapter;
import com.mc.adapter.ProgramStudyAdapter;
import com.mc.books.R;
import com.mc.common.fragments.BaseMvpFragment;
import com.mc.models.home.Author;
import com.mc.models.home.ProgramStudy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

public class InformationBookFragment extends BaseMvpFragment<IInformationBookView, IInformationBookPresenter<IInformationBookView>> implements IInformationBookView {

    @BindView(R.id.txtGeneral)
    ExtTextView txtGeneral;
    @BindView(R.id.txtReadMore)
    ExtTextView txtReadMore;
    @BindView(R.id.txtReadMoreProgramStudy)
    ExtTextView txtReadMoreProgramStudy;
    @BindView(R.id.rvAuthor)
    RecyclerView rvAuthor;
    @BindView(R.id.rvProgramStudy)
    RecyclerView rvProgramStudy;
    @BindView(R.id.llProgramStudy)
    LinearLayout llProgramStudy;
    @BindView(R.id.llCommunityBook)
    LinearLayout llCommunityBook;

    private boolean isReadMoreGeneal = false;
    private boolean isReadMoreProgramStudy = false;
    private AuthorAdapter authorAdapter;
    private ProgramStudyAdapter programStudyAdapter;
    private List<ProgramStudy> programStudyList;

    public static InformationBookFragment newInstance() {
        Bundle args = new Bundle();
        InformationBookFragment fragment = new InformationBookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public IInformationBookPresenter<IInformationBookView> createPresenter() {
        return new InformationBookPresenter<>(getAppComponent());
    }

    @Override
    public int getResourceId() {
        return R.layout.information_book_fragment;
    }

    @Override
    public int getTitleId() {
        return R.string.information_book;
    }

    @Override
    public void initToolbar(@NonNull ActionBar supportActionBar) {
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_right);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindButterKnife(view);

        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author("Miao", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRyB-ItAjLW6s4vnOfo79COlFAGZ_8m-WRlNVEji91QGEF4A0Cb"));
        authorList.add(new Author("Miao1", "data:image/jpeg;base64,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"));
        authorList.add(new Author("Miao2", "https://i1.sndcdn.com/avatars-000294031913-mby9i9-t500x500.jpg"));
        authorAdapter = new AuthorAdapter(getAppContext());
        rvAuthor.setLayoutManager(new GridLayoutManager(getAppContext(), 2));
        rvAuthor.setAdapter(authorAdapter);
        rvAuthor.setNestedScrollingEnabled(false);
        authorAdapter.setDataList(authorList);

        programStudyList = new ArrayList<>();
        programStudyList.add(new ProgramStudy(1, "Phần 1: Bài nghe"));
        programStudyList.add(new ProgramStudy(2, "Phần 2: Bài nghe"));
        programStudyList.add(new ProgramStudy(3, "Phần 3: Bài nghe"));
        programStudyList.add(new ProgramStudy(4, "Phần 4: Bài nghe"));
        programStudyList.add(new ProgramStudy(5, "Phần 5: Bài nghe"));
        programStudyList.add(new ProgramStudy(6, "Phần 6: Bài nghe"));
        programStudyList.add(new ProgramStudy(7, "Phần 7: Bài nghe"));
        programStudyList.add(new ProgramStudy(8, "Phần 8: Bài nghe"));
        programStudyList.add(new ProgramStudy(9, "Phần 9: Bài nghe"));
        programStudyList.add(new ProgramStudy(10, "Phần 10: Bài nghe"));
        programStudyAdapter = new ProgramStudyAdapter(getAppContext(), programStudy -> this.goToDetailProgramStudy(programStudy.getId()));
        rvProgramStudy.setLayoutManager(new LinearLayoutManager(getAppContext()));
        rvProgramStudy.setAdapter(programStudyAdapter);
        rvProgramStudy.setNestedScrollingEnabled(false);

        if (programStudyList.size() > 6) {
            programStudyAdapter.setDataList(StreamSupport.stream(programStudyList).limit(5).collect(Collectors.toList()));
            txtReadMoreProgramStudy.setVisibility(View.VISIBLE);
        } else {
            programStudyAdapter.setDataList(programStudyList);
        }

        txtGeneral.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = txtGeneral.getLineCount();
                if (lineCount > 3) {
                    txtGeneral.setMaxLines(3);
                    txtReadMore.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    void goToDetailProgramStudy(int programStudyId) {

    }

    @OnClick({R.id.txtReadMore, R.id.llProgramStudy, R.id.txtReadMoreProgramStudy, R.id.llCommunityBook})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtReadMore:
                if (isReadMoreGeneal) {
                    isReadMoreGeneal = false;
                    txtGeneral.setMaxLines(3);
                    txtReadMore.setText(R.string.showMore);
                } else {
                    isReadMoreGeneal = true;
                    txtGeneral.setMaxLines(99);
                    txtReadMore.setText(R.string.showLess);
                }
                break;
            case R.id.llProgramStudy:
                break;
            case R.id.txtReadMoreProgramStudy:
                if (isReadMoreProgramStudy) {
                    isReadMoreProgramStudy = false;
                    programStudyAdapter.setDataList(StreamSupport.stream(programStudyList).limit(5).collect(Collectors.toList()));
                    txtReadMoreProgramStudy.setText(R.string.showMore);
                } else {
                    isReadMoreProgramStudy = true;
                    programStudyAdapter.setDataList(programStudyList);
                    txtReadMoreProgramStudy.setText(R.string.showLess);
                }
                break;
            case R.id.llCommunityBook:
                break;
        }
    }
}
