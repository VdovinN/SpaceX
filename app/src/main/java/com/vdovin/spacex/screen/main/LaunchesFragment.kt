package com.vdovin.spacex.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.vdovin.spacex.R
import com.vdovin.spacex.base.BaseFragment
import com.vdovin.spacex.base.BasePresenter
import com.vdovin.spacex.database.model.SpaceX
import com.vdovin.spacex.screen.details.LaunchDetailsFragment
import com.vdovin.spacex.util.add
import com.vdovin.spacex.util.showSnackBar
import kotlinx.android.synthetic.main.launches_layout.*
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named


class LaunchesFragment : BaseFragment<LaunchesView>(), LaunchesView {
    override val view: LaunchesView = this

    override val presenter: BasePresenter<LaunchesView> by inject(named(TAG))

    private lateinit var launchesAdapter: LaunchesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.launches_layout, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launches_recycler_view.layoutManager = LinearLayoutManager(context)
        launchesAdapter = LaunchesAdapter(context, ArrayList())
        launches_recycler_view.adapter = launchesAdapter
    }

    override fun displayLaunches(launches: List<SpaceX>) {
        launchesAdapter.swap(launches);
    }

    override fun openDetails(spaceX: SpaceX) {
        activity?.add(LaunchDetailsFragment.newInstance(spaceX.flightNumber), R.id.main_container, LaunchDetailsFragment.TAG)
    }

    override fun launchClicked(launchClickListener: (SpaceX) -> Unit) {
        launchesAdapter.onItemClick = { spaceX -> launchClickListener.invoke(spaceX) }
    }

    override fun showError(message: String?) {
        parent_view.showSnackBar(getString(R.string.error_message, message))
    }

    companion object {
        const val TAG = "launches_fragment"
        fun newInstance() = LaunchesFragment()
    }
}