import { ClipboardCheck, HelpCircle } from "lucide-react";
import { useState, type FormEvent } from "react";
import { useTranslation } from "react-i18next";

import { useLogin } from "../hooks/useLogin";
import LanguageSwitcher from "./LanguageSwitcher";

const LoginForm = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [rememberMe, setRememberMe] = useState(false);
  const [validationError, setValidationError] = useState("");

  const { handleLogin, loading, error } = useLogin();
  const { t } = useTranslation();

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setValidationError("");

    if (!username.trim() || !password) {
      setValidationError(t("login.validationRequired"));
      return;
    }

    try {
      await handleLogin(username.trim(), password);
    } catch {
      // useLogin should manage and display API errors.
    }
  };

  return (
    <div className="flex min-h-screen flex-col overflow-x-hidden bg-neutral-100 font-sans text-neutral-800">
      {/* Header */}
      <header className="flex items-center justify-between px-4 py-4 sm:px-6 sm:py-6 lg:px-8">
        <div className="text-lg font-bold tracking-tight text-neutral-900">
          {t("app.name")}
        </div>

        <div className="flex items-center gap-3">
          <LanguageSwitcher />

          <button
            type="button"
            aria-label={t("login.help")}
            className="text-neutral-500 transition-colors hover:text-neutral-700"
          >
            <HelpCircle className="h-5 w-5" />
          </button>
        </div>
      </header>

      {/* Main */}
      <main className="flex flex-1 items-center justify-center px-4 py-6">
        <section className="w-full max-w-md border border-neutral-200 bg-white p-5 shadow-sm sm:p-8">
          {/* Heading */}
          <div className="mb-8 flex flex-col items-center">
            <div className="mb-4 bg-black p-3 text-white">
              <ClipboardCheck className="h-6 w-6" />
            </div>

            <h1 className="mb-2 text-center text-lg font-semibold text-neutral-900 sm:text-xl">
              {t("login.title")}
            </h1>

            <p className="text-center text-xs text-neutral-500">
              {t("login.subtitle")}
            </p>
          </div>

          {/* Login Form */}
          <form onSubmit={handleSubmit} className="space-y-4" noValidate>
            {/* Username */}
            <div>
              <label
                htmlFor="username"
                className="mb-1.5 block text-[10px] font-bold uppercase tracking-wider text-neutral-500"
              >
                {t("login.username")}
              </label>

              <input
                id="username"
                name="username"
                type="text"
                autoComplete="username"
                placeholder={t("login.usernamePlaceholder")}
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                disabled={loading}
                required
                aria-invalid={Boolean(validationError)}
                className="w-full border border-neutral-300 px-3 py-2.5 text-sm outline-none placeholder:text-neutral-400 focus:border-black disabled:cursor-not-allowed disabled:bg-neutral-100"
              />
            </div>

            {/* Password */}
            <div>
              <label
                htmlFor="password"
                className="mb-1.5 block text-[10px] font-bold uppercase tracking-wider text-neutral-500"
              >
                {t("login.password")}
              </label>

              <input
                id="password"
                name="password"
                type="password"
                autoComplete="current-password"
                placeholder={t("login.passwordPlaceholder")}
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                disabled={loading}
                required
                aria-invalid={Boolean(validationError || error)}
                className="w-full border border-neutral-300 px-3 py-2.5 text-sm outline-none placeholder:text-neutral-400 focus:border-black disabled:cursor-not-allowed disabled:bg-neutral-100"
              />
            </div>

            {/* Remember Me */}
            <div className="flex items-center justify-between">
              <label className="flex cursor-pointer items-center gap-2 text-xs text-neutral-600">
                <input
                  type="checkbox"
                  checked={rememberMe}
                  onChange={(e) => setRememberMe(e.target.checked)}
                  disabled={loading}
                  className="h-4 w-4 accent-black"
                />

                <span>{t("login.rememberMe")}</span>
              </label>
            </div>

            {/* Validation Error */}
            {validationError && (
              <p role="alert" className="text-sm text-red-600">
                {validationError}
              </p>
            )}

            {/* API Error */}
            {error && (
              <p role="alert" className="text-sm text-red-600">
                {error}
              </p>
            )}

            {/* Submit Button */}
            <button
              type="submit"
              disabled={loading}
              className="w-full bg-black py-3 text-xs font-semibold uppercase tracking-wider text-white transition-colors hover:bg-neutral-800 disabled:cursor-not-allowed disabled:opacity-50"
            >
              {loading ? t("login.loggingIn") : t("login.login")}
            </button>
          </form>

          {/* Additional Links */}
          <div className="mt-6 space-y-2 border-t border-neutral-100 pt-6 text-center">
            <div>
              <button
                type="button"
                className="text-xs font-medium text-neutral-700 hover:underline"
              >
                {t("login.forgotPassword")}
              </button>
            </div>

            <div className="text-xs text-neutral-500">
              {t("login.noAccount")}{" "}
              <button
                type="button"
                className="font-medium text-neutral-900 hover:underline"
              >
                {t("login.requestAccess")}
              </button>
            </div>
          </div>
        </section>
      </main>

      {/* Footer */}
      <footer className="border-t border-neutral-200 px-4 py-6 sm:px-6 lg:px-8">
        <div className="flex flex-col items-center justify-between gap-4 text-[11px] text-neutral-500 lg:flex-row">
          <div className="font-semibold tracking-tight text-neutral-800">
            INVENTORYFLOW
          </div>

          <nav
            aria-label="Footer navigation"
            className="flex flex-wrap justify-center gap-4 tracking-wide"
          >
            <a href="#privacy" className="hover:text-neutral-800">
              {t("footer.privacy")}
            </a>

            <a href="#terms" className="hover:text-neutral-800">
              {t("footer.terms")}
            </a>

            <a href="#help" className="hover:text-neutral-800">
              {t("footer.helpCenter")}
            </a>
          </nav>

          <div className="text-center">{t("footer.copyright")}</div>
        </div>
      </footer>
    </div>
  );
};

export default LoginForm;
