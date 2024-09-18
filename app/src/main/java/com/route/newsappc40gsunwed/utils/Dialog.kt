package com.route.newsappc40gsunwed.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.route.newsappc40gsunwed.Colors
import com.route.newsappc40gsunwed.R

@Composable
fun ErrorDialog(
    errorState: MutableIntState,
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit,
) {
    if (errorState.intValue != R.string.empty) {
        AlertDialog(onDismissRequest = {
            errorState.intValue = R.string.empty
        }, confirmButton = {
            TextButton(onClick = {
                onRetryClick()
                errorState.intValue = R.string.empty
            }) {
                Text(text = stringResource(R.string.retry), color = Colors.green)
            }
        }, text = {
            Text(text = stringResource(id = errorState.intValue))
        }, dismissButton = {
            TextButton(onClick = {
                errorState.intValue = R.string.empty
            }) {
                Text(text = stringResource(R.string.dismiss), color = Colors.green)
            }
        })
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorDialogPreview() {
    val state = remember {
        mutableIntStateOf(R.string.check_your_internet_connection)
    }
    ErrorDialog(state) {}
}
