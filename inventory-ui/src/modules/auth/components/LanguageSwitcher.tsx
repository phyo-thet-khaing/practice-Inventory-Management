import { Globe } from "lucide-react";
import { useTranslation } from "react-i18next";

const LanguageSwitcher = () => {
  const { i18n } = useTranslation();

  const currentLanguage = i18n.language;

  const changeLanguage = (language: "en" | "my") => {
    i18n.changeLanguage(language);
    localStorage.setItem("language", language);
  };

  return (
    <div className="flex items-center gap-1 rounded-full border border-neutral-200 bg-white p-1 shadow-sm">
      <Globe className="w-4 h-4 text-neutral-500 ml-2" />

      <button
        type="button"
        onClick={() => changeLanguage("en")}
        className={`rounded-full px-3 py-1.5 text-xs font-medium transition-all ${
          currentLanguage === "en"
            ? "bg-black text-white shadow-sm"
            : "text-neutral-500 hover:text-neutral-900"
        }`}
      >
        EN
      </button>

      <button
        type="button"
        onClick={() => changeLanguage("my")}
        className={`rounded-full px-3 py-1.5 text-xs font-medium transition-all ${
          currentLanguage === "my"
            ? "bg-black text-white shadow-sm"
            : "text-neutral-500 hover:text-neutral-900"
        }`}
      >
        မြန်မာ
      </button>
    </div>
  );
};

export default LanguageSwitcher;
