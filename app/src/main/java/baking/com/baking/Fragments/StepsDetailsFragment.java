package baking.com.baking.Fragments;

import android.content.Context;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.FileDataSource.FileDataSourceException;
import com.google.android.exoplayer2.util.Util;

import java.io.FileNotFoundException;

import baking.com.baking.R;

public class StepsDetailsFragment extends Fragment implements Player.EventListener{

    private VideoView videoView;
    private TextView stepDescription;
    private String videoUrl;
    private String description;
    private String thumbnailUrl;
    private RelativeLayout relativeLayout;
    private Toolbar activity_toolbar ;
    private Toolbar fragment_toolbar;
    private View view;
    private PlayerView playerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.steps_details_layout,container,false);
        playerView = view.findViewById(R.id.video_view);
        removeActivityToolbar();
        final int position = getArguments().getInt("RecipeId");
        fragment_toolbar = view.findViewById(R.id.steps_details_toolbar);
        fragment_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("RecipeId" , position);
                bundle.putInt("Position",1);
                IngredientAndStepsContainerFragment ingredientAndStepsContainerFragment = new IngredientAndStepsContainerFragment();
                ingredientAndStepsContainerFragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.recipe_frag, ingredientAndStepsContainerFragment, "ing_frag").commit();

            }
        });
        stepDescription = view.findViewById(R.id.step_description);
        videoUrl = getArguments().getString("VideoUrl");
        description = getArguments().getString("StepsDescription");
        thumbnailUrl = getArguments().getString("Thumbnail");
        stepDescription.setText(description);
        //setVideoView();
        getExoPlayer();
        return  view;
    }

    private void setVideoView() {
        videoView = view.findViewById(R.id.video_view);
        if(!videoUrl.isEmpty()) {
            videoView.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared() {
                    videoView.start();
                }
            });
            videoView.setVideoURI(Uri.parse(videoUrl));
        }
        else {
            videoView.getVideoControls().hide();
            videoView.setPreviewImage(R.drawable.novideo);
        }

    }

    private void removeActivityToolbar(){
        relativeLayout = getActivity().findViewById(R.id.activity_parent);
        activity_toolbar = getActivity().findViewById(R.id.activity_toolbar);
        relativeLayout.removeView(activity_toolbar);
    }

    public void getExoPlayer() {
        if(!videoUrl.isEmpty()) {


        Context context = getContext();

        // 1. Create a default TrackSelector
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        // 2. Create a default LoadControl
        LoadControl loadControl = new DefaultLoadControl();
        // 3. Create the player
        SimpleExoPlayer player =
                ExoPlayerFactory.newSimpleInstance(context, trackSelector);

            playerView.setPlayer(player);
            playerView.setKeepScreenOn(true);
            // Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(context, "ExoPlayer"));

            // Produces Extractor instances for parsing the media data.
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();


            // This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(videoUrl));
            // Prepare the player with the source.
            player.addListener(this);
            player.prepare(videoSource);
            playerView.requestFocus();
            player.setPlayWhenReady(true);
        }
        else{
            playerView.removeAllViews();
             playerView.setBackground(getResources().getDrawable(R.drawable.novideo));
        }




    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {



    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }
}
