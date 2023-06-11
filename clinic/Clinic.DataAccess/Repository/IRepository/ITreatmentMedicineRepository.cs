using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface ITreatmentMedicineRepository : IRepositoryAsync<TreatmentMedicine>
    {
        void Update(TreatmentMedicine treatmentMedicine);
    }
}