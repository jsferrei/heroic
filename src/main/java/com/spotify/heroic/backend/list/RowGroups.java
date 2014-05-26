package com.spotify.heroic.backend.list;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.spotify.heroic.backend.list.FindRowGroupsReducer.PreparedGroup;
import com.spotify.heroic.model.TimeSerieSlice;

@RequiredArgsConstructor
public class RowGroups {
    @Getter
    private final Map<TimeSerieSlice, List<PreparedGroup>> groups;
}