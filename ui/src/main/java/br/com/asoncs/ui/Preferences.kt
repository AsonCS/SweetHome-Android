package br.com.asoncs.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.*

class PrefsScreen : AppCompatActivity() {


    /**
     * Referência da instância do fragmento de preferências.
     */
    private var fragment = PrefsFragment()

    /**
     * Cria a View.
     *
     * @param savedInstanceState Bundle.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragment
        // Deixa a tela de preferências vísivel para o usuário
        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, fragment)
            .commit()
    }

    class PrefsFragment : PreferenceFragmentCompat() {

        private var ip: String? = null
        private var ipRoute: Preference? = null
        private lateinit var prefs: SharedPreferences

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preference_body, rootKey)
        }

        /**
         * Monta os summary's que vão aparecer na tela de preferências.
         *
         * @param inflater LayoutInflater.
         * @param container ViewGroup.
         * @param savedInstanceState Bundle.
         * @return View
         */
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = super.onCreateView(inflater, container, savedInstanceState)

            view?.background = context?.getDrawable(R.color.colorPrimary)

            // Pega as preferências atuais
            prefs = PreferenceManager.getDefaultSharedPreferences(activity)
            // Pega os valores
            ip = prefs.getString("ip_route", "exemplo: XXX.XX.XXX.XX:XXXX/app")
            // Pega os summary's
            ipRoute = findPreference("ip_route")
            // Muda os summary's
            ipRoute!!.summary = ip.toString()
            // Configura ouvintes de mundanças
            ipRoute!!.onPreferenceChangeListener = ChangeIPRoute()

            return view
        }

        /**
         * Atualiza os summary's da tela de preferências.
         */
        override fun onResume() {
            // Muda os summary's
            findPreference<Preference?>("ip_route")?.summary = ip.toString()
            super.onResume()
        }

        /**
         * Classe que trata mudanças no ip_route e atualiza seu summary.
         */
        private inner class ChangeIPRoute : Preference.OnPreferenceChangeListener {
            override fun onPreferenceChange(preference: Preference, newValue: Any): Boolean {
                if (ipRoute != null) {
                    // Muda o summary
                    ipRoute!!.summary = newValue.toString()
                }
                return true
            }
        }
    }
}